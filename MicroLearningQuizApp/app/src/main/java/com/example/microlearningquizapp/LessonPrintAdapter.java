// Fail: LessonPrintAdapter.java
package com.example.microlearningquizapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.CancellationSignal;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;

import com.example.microlearningquizapp.model.LessonAdmin;

import java.util.ArrayList;

public class LessonPrintAdapter extends PrintDocumentAdapter {

    private Context context;
    private ArrayList<LessonAdmin> lessonList;
    private PdfDocument pdfDocument;

    public LessonPrintAdapter(Context context, ArrayList<LessonAdmin> lessonList) {
        this.context = context;
        this.lessonList = lessonList;
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes,
                         CancellationSignal cancellationSignal,
                         LayoutResultCallback callback, android.os.Bundle extras) {

        pdfDocument = new PdfDocument();

        PrintDocumentInfo info = new PrintDocumentInfo
                .Builder("lessons.pdf")
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .setPageCount(PrintDocumentInfo.PAGE_COUNT_UNKNOWN)
                .build();

        callback.onLayoutFinished(info, true);
    }

    @Override
    public void onWrite(android.print.PageRange[] pages,
                        android.os.ParcelFileDescriptor destination,
                        CancellationSignal cancellationSignal,
                        WriteResultCallback callback) {

        try {
            int pageWidth = 595;
            int pageHeight = 842;
            int margin = 50;
            int y = margin;

            Paint paint = new Paint();
            paint.setTextSize(12f);

            int pageNumber = 1;
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create();
            PdfDocument.Page page = pdfDocument.startPage(pageInfo);
            Canvas canvas = page.getCanvas();

            for (LessonAdmin lesson : lessonList) {

                if (y > pageHeight - margin) {
                    pdfDocument.finishPage(page);
                    pageNumber++;
                    pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create();
                    page = pdfDocument.startPage(pageInfo);
                    canvas = page.getCanvas();
                    y = margin;
                }

                canvas.drawText("Title: " + (lesson.getLesson_title() != null ? lesson.getLesson_title() : ""), margin, y, paint);
                canvas.drawText("Subject: " + (lesson.getSubject() != null ? lesson.getSubject() : ""), margin, y + 20, paint);
                canvas.drawText("Year: " + (lesson.getYear() != null ? lesson.getYear() : ""), margin, y + 40, paint);

                y += 70;
            }

            pdfDocument.finishPage(page);
            pdfDocument.writeTo(new java.io.FileOutputStream(destination.getFileDescriptor()));
            callback.onWriteFinished(new android.print.PageRange[]{android.print.PageRange.ALL_PAGES});

        } catch (Exception e) {
            e.printStackTrace();
            callback.onWriteFailed(e.getMessage());
        } finally {
            if (pdfDocument != null) {
                pdfDocument.close();
            }
        }
    }
}





