package com.inkd.auth.repository.pdf;

import com.inkd.auth.model.domain.pdf.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository extends JpaRepository<PdfFile, Long> {
}
