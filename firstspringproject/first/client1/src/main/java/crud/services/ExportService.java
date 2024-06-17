package crud.services;

import java.io.IOException;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import crud.exporters.FilesExporter;
import crud.service.dto.UserDto;
import lombok.Data;

@Data
public class ExportService {

	private RestTemplate rest = new RestTemplate();

	private FilesExporter exporter = new FilesExporter();

	public void exportToExcel() throws IOException{
		HttpEntity<UserDto> requestEntity = new HttpEntity<UserDto>(new HttpHeaders());
		ParameterizedTypeReference<List<UserDto>> responseType = new ParameterizedTypeReference<List<UserDto>>() {};
		ResponseEntity<List<UserDto>> resp = rest.exchange("http://localhost:9090/api/users", HttpMethod.GET, requestEntity, responseType);
		var list = resp.getBody();
		exporter.exportToExcel(list);
	}
}
