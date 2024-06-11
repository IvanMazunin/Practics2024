package crud.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import crud.exporters.FilesExporter;
import crud.service.dto.UserDto;

@RestController
@RequestMapping(path = "/api")
public class ExportController {
	private RestTemplate rest = new RestTemplate();

	private FilesExporter export = new FilesExporter();

	@GetMapping("/users/export/excel")
	public void download() throws IOException{
		HttpEntity<UserDto> requestEntity = new HttpEntity<UserDto>(new HttpHeaders());
		ParameterizedTypeReference<List<UserDto>> responseType = new ParameterizedTypeReference<List<UserDto>>() {};
		ResponseEntity<List<UserDto>> resp = rest.exchange("http://localhost:9090/api/users", HttpMethod.GET, requestEntity, responseType);
		var list = resp.getBody();
		export.exportToExcel(list);
	};
}
