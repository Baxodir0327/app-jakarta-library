package com.company.servlet;

import com.company.dao.UploadDAO;
import com.company.entity.Upload;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(name = "FileStorageDownloadServlet", urlPatterns = "/file/download")
public class FileStorageDownloadServlet extends HttpServlet {
    private static final Path rootPath = Path.of("/home/jlkesh/Desktop/uploads/lib");
    private static final UploadDAO uploadDAO = new UploadDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileID = request.getParameter("fileID");
        Upload upload = uploadDAO.findById(fileID);
        Path path = rootPath.resolve(upload.getGeneratedName());
        byte[] bytes = Files.readAllBytes(path);
        response.addHeader("Content-Type", upload.getMimeType());
        response.addHeader("Content-Disposition", "attachment; filename=" + upload.getOriginalName());
        response.getOutputStream().write(bytes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
