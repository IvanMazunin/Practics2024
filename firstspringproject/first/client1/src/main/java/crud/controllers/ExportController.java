package crud.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.services.ExportService;

@RestController
@RequestMapping(path = "/api")
public class ExportController {

	private ExportService service = new ExportService();

	@GetMapping("/users/export/excel")
	public void download() throws IOException{
		service.exportToExcel();
	};
}
