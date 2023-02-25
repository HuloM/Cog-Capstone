package com.example.capstoneproject.service;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class FileRenameService {
    private String currentFileName;
}
