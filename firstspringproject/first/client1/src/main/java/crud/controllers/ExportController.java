package crud.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import crud.exporters.FilesExporter;
import crud.services.ExportService;

@RestController
@RequestMapping(path = "/api")
public class ExportController {
	private RestTemplate rest = new RestTemplate();

	private FilesExporter export = new FilesExporter();

	private ExportService service = new ExportService();

	@GetMapping("/users/export/excel")
	public void download() throws IOException{
		export.exportToExcel(service.getUserDtos(rest));
	};
}
