package com.bolsadeideas.springboot.sistema.app.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public Resource cargar(String nombreFoto, String sourcePath) throws MalformedURLException;
	
	public String copiar(MultipartFile archivo, String sourcePath) throws IOException;
	
	public boolean eliminar(String nombreFoto,  String sourcePath);
	
	public Path getPath(String nombreFoto, String sourcePath);
}
