package com.inkd.auth.model.domain.pdf;

import com.inkd.auth.model.domain.event.Event;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pdf_file")
public class PdfFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PDF_ID")
    private Long pdfID;

    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createDate;

    public Long getPdfID() {
        return pdfID;
    }

    public void setPdfID(Long pdfID) {
        this.pdfID = pdfID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
