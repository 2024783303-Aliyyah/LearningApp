// Fail: LessonPrintAdapter.java
package com.example.microlearningquizapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LessonPrintAdapter extends PrintDocumentAdapter {

    private Context context;
    private ArrayList<LessonAdmin> lessonList;
    private int pageHeight;
    private int pageWidth;
    private PdfDocument myPdfDocument;
    private int totalpages = 1; // Andaikan semua muat dalam satu mukasurat pada mulanya

    public LessonPrintAdapter(Context context, ArrayList<LessonAdmin> lessonList) {
        this.context = context;
        this.lessonList = lessonList;
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras) {
        // Simpan atribut cetakan
        myPdfDocument = new PdfDocument();
        pageHeight = newAttributes.getMediaSize().getHeightMils() * 72 / 1000;
        pageWidth = newAttributes.getMediaSize().getWidthMils() * 72 / 1000;

        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }

        // Bina maklumat dokumen untuk dihantar ke print preview
        PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                .Builder("lessons_report.pdf")
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .setPageCount(totalpages);

        PrintDocumentInfo info = builder.build();
        callback.onLayoutFinished(info, true);
    }

    @Override
    public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback) {
        // Mula melukis kandungan ke halaman PDF
        PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
        PdfDocument.Page page = myPdfDocument.startPage(newPage);

        if (cancellationSignal.isCanceled()) {
            callback.onWriteCancelled();
            myPdfDocument.close();
            myPdfDocument = null;
            return;
        }

        // Lukis kandungan ke canvas
        drawPage(page);

        // Selesai melukis
        myPdfDocument.finishPage(page);

        // Tulis dokumen PDF ke fail destinasi
        try {
            myPdfDocument.writeTo(new FileOutputStream(destination.getFileDescriptor()));
        } catch (IOException e) {
            callback.onWriteFailed(e.toString());
            return;
        } finally {
            myPdfDocument.close();
            myPdfDocument = null;
        }

        callback.onWriteFinished(pageRanges);
    }

    private void drawPage(PdfDocument.Page page) {
        Canvas canvas = page.getCanvas();

        // Tetapan untuk "brush" (Paint)
        Paint titlePaint = new Paint();
        titlePaint.setColor(Color.BLACK);
        titlePaint.setTextSize(20);
        titlePaint.setFakeBoldText(true);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(14);

        int leftMargin = 54;
        int topMargin = 54;
        int currentY = topMargin;

        // Lukis Tajuk
        canvas.drawText("List of All Lessons", leftMargin, currentY, titlePaint);
        currentY += 40;

        // Lukis setiap item pelajaran
        for (LessonAdmin lesson : lessonList) {
            canvas.drawText("Title: " + lesson.getTitle(), leftMargin, currentY, textPaint);
            currentY += 20;
            canvas.drawText("Subject: " + lesson.getSubject() + " (" + lesson.getYear() + ")", leftMargin + 10, currentY, textPaint);
            currentY += 30; // Ruang antara setiap pelajaran
        }

        // Lukis jumlah pelajaran di bahagian bawah
        currentY += 40;
        titlePaint.setTextSize(16);
        canvas.drawText("Total Lessons: " + lessonList.size(), leftMargin, currentY, titlePaint);
    }
}
