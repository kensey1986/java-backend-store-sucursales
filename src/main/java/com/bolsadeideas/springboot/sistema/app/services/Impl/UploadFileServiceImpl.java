package com.bolsadeideas.springboot.sistema.app.services.Impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.sistema.app.services.IUploadFileService;

@Service
public class UploadFileServiceImpl implements IUploadFileService{
	
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	
	

	@Override
	public Resource cargar(String nombreFoto, String sourcePath) throws MalformedURLException {
		
		Path rutaArchivo = getPath(nombreFoto, sourcePath);
		log.info(rutaArchivo.toString());
		
		Resource recurso = new UrlResource(rutaArchivo.toUri());
		
		if(!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			
			recurso = new UrlResource(rutaArchivo.toUri());
			
			log.error("Error no se pudo cargar la imagen: " + nombreFoto);
			
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo, String sourcePath) throws IOException {
		
		String nombreArchivo = UUID.randomUUID().toString() + "_" +  archivo.getOriginalFilename().replace(" ", "");
		
		Path rutaArchivo = getPath(nombreArchivo, sourcePath);
		log.info(rutaArchivo.toString());
		
		Files.copy(archivo.getInputStream(), rutaArchivo);
		
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto,  String sourcePath) {
		
		if(nombreFoto !=null && nombreFoto.length() >0) {
			Path rutaFotoAnterior = Paths.get(sourcePath).resolve(nombreFoto).toAbsolutePath();
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Path getPath(String nombreFoto, String sourcePath) {
		return Paths.get(sourcePath).resolve(nombreFoto).toAbsolutePath();
	}

}
