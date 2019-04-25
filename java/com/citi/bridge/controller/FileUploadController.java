package com.citi.bridge.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.validation.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citi.bridge.models.AllRecords;
import com.citi.bridge.models.MRecords;
import com.citi.bridge.models.SS;
import com.citi.bridge.models.SSFiles;
import com.citi.bridge.models.SSInvalidFiles;
import com.citi.bridge.models.ScreenedRecords;
import com.citi.bridge.models.UploadedFiles;
import com.citi.bridge.repositories.AllRecordsRepository;
import com.citi.bridge.repositories.SSFilesrepository;
import com.citi.bridge.repositories.SSInvalidFilesRepository;
import com.citi.bridge.repositories.SSMaliciousRecords;
import com.citi.bridge.repositories.SSRepository;
import com.citi.bridge.repositories.SSScreenedRepository;
import com.citi.bridge.repositories.SSUploadedFilesRepository;
import com.citi.bridge.storage.StorageFileNotFoundException;
import com.citi.bridge.storage.StorageService;
import com.citi.bridge.transaction.Transaction;
import com.citi.bridge.transaction.TransactionManager;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private SSRepository ssR;

	@Autowired
	private AllRecordsRepository allR;

	@Autowired
	private SSFilesrepository ssFR;

	@Autowired
	private SSInvalidFilesRepository ssIFR;

	@Autowired
	private SSMaliciousRecords ssmR;
	@Autowired
	private SSScreenedRepository ssSR;

	@Autowired
	private SSUploadedFilesRepository upFiles;

	TransactionManager t = TransactionManager.getInstance();
	ArrayList<Transaction> vTrans;
	ArrayList<Transaction> inTrans;

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		logger.info("In Fileuploadctor");
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		logger.info("In File upload Mapping .. ");

		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/handleFileUpload")
	public void handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calobj = Calendar.getInstance();
		String s = df.format(calobj.getTime());

		logger.info(" Segregating records ... ");

		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		java.nio.file.Path filePath = Paths.get("D:/uploadedfiles" + "/" + file.getOriginalFilename());

		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		File file2 = ResourceUtils.getFile("classpath:upload-dir/" + file.getOriginalFilename());

		String lines[];
		lines = Files.readAllLines(file2.toPath()).toArray(new String[0]); // path of the file in quotes

		t.readTransactions(lines);

		vTrans = t.getValidTransaction();
		inTrans = t.getinvalidTransaction();

		SSFiles ssf = new SSFiles();
		SSInvalidFiles ssif = new SSInvalidFiles();
		AllRecords allRe;
		UploadedFiles upfiles = new UploadedFiles();

		int size = vTrans.size();
		int insize = inTrans.size();

		logger.info("Valid Records ... ");

		for (int i = 0; i < size; i++) {
			
			allRe = new AllRecords(vTrans.get(i).getRefNumber(),vTrans.get(i).getDate(),vTrans.get(i).getPayerName(),vTrans.get(i).getPayerAccount(),vTrans.get(i).getPayeeName(),vTrans.get(i).getPayeeAccount(),vTrans.get(i).getAmount(),file.getOriginalFilename(),"ValidPass");
			ssf.setTransactionRef(vTrans.get(i).getRefNumber());

			ssf.setValueDate(vTrans.get(i).getDate());

			ssf.setPayerName(vTrans.get(i).getPayerName());

			ssf.setPayerAccount(vTrans.get(i).getPayerAccount());

			ssf.setPayeeName(vTrans.get(i).getPayeeName());

			ssf.setPayeeAccount(vTrans.get(i).getPayeeAccount());

			ssf.setAmount(vTrans.get(i).getAmount());

			ssf.setFileName(file.getOriginalFilename());		

			allRe.setStatus("ValidPass");

			logger.info(ssf.toString());
			ssFR.save(ssf);
			allR.save(allRe);
		}

		logger.info("Invalid records ...");
		for (int i = 0; i < insize; i++) {
			ssif.setTransactionRef(inTrans.get(i).getRefNumber());
			allRe.setTransactionRef(inTrans.get(i).getRefNumber());

			ssif.setValueDate(inTrans.get(i).getDate());
			allRe.setValueDate(inTrans.get(i).getDate());

			ssif.setPayerName(inTrans.get(i).getPayerName());
			allRe.setPayerName(inTrans.get(i).getPayerName());

			ssif.setPayerAccount(inTrans.get(i).getPayerAccount());
			allRe.setPayerAccount(inTrans.get(i).getPayerAccount());

			ssif.setPayeeName(inTrans.get(i).getPayeeName());
			allRe.setPayeeName(inTrans.get(i).getPayeeName());

			ssif.setPayeeAccount(inTrans.get(i).getPayeeAccount());
			allRe.setPayeeAccount(inTrans.get(i).getPayeeAccount());

			ssif.setAmount(inTrans.get(i).getAmount());
			allRe.setAmount(inTrans.get(i).getAmount());

			ssif.setFileName(file.getOriginalFilename());
			allRe.setFileName(file.getOriginalFilename());

			allRe.setStatus("ValidFail");

			ssIFR.save(ssif);
			allR.save(allRe);
		}

		upfiles.setFileName(file.getOriginalFilename());
		upfiles.setDateOfUploading(s);

		logger.info(upfiles.getFileName());

		upFiles.save(upfiles);

		file2.renameTo(new File("D:\\ArchiveDirectory\\" + file.getOriginalFilename()));

		vTrans.clear();
		inTrans.clear();

	}

	@PostMapping("/Users")
	public SS createUsers(@Valid @RequestBody SS ss) {

		return ssR.save(ss);
	}

	@GetMapping("/screenedrecords")
	public List<ScreenedRecords> getAllScreenedRecords() {
		// Sort sortByUserNameAtDesc = new Sort(Sort.Direction.DESC, "userName");
		logger.info("Getting Screened records ...");
		return ssSR.findAll();
	}

	@GetMapping("/records")
	public List<SSInvalidFiles> getAllRecords() {
		// Sort sortByUserNameAtDesc = new Sort(Sort.Direction.DESC, "userName");
		logger.info("Getting Invalid file records ...");
		return ssIFR.findAll();
	}

	@GetMapping("/validrecords")
	public List<SSFiles> getAllInvalidRecords() {
		// Sort sortByUserNameAtDesc = new Sort(Sort.Direction.DESC, "userName");
		logger.info("Getting Valid file records ...");
		return ssFR.findAll();
	}

	@GetMapping("/Users")
	public List<SS> getAllUsers() {
		// Sort sortByUserNameAtDesc = new Sort(Sort.Direction.DESC, "userName");
		logger.info("Getting all Users ...");
		return ssR.findAll();
	}

	@GetMapping("/maliciousrecords")
	public List<MRecords> getAllMaliciousRecords() {
		logger.info("Getting malicious records ...");
		return ssmR.findAll();
	}

	@GetMapping("/uploadFiles")
	public List<UploadedFiles> getAllFiles() {
		logger.info("Getting all files ...");
		return upFiles.findAll();
	}

	@GetMapping("/allrecords")
	public List<AllRecords> getAllUpRecords() {
		// Sort sortByUserNameAtDesc = new Sort(Sort.Direction.DESC, "userName");
		logger.info("Getting All records ...");
		return allR.findAll();
	}

	@PostMapping
	public void createUsers(@Valid @RequestBody ScreenedRecords ss[]) {

		int i = 0;
		for (i = 0; i < ss.length; i++) {
			ssSR.save(ss[i]);
		}
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
