// Fail: StudentScorePrintAdapter.java
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
import java.util.List;

public class StudentScorePrintAdapter extends PrintDocumentAdapter {

    private Context context;
    private List<StudentOverallScore> studentList;
    private int pageHeight;
    private int pageWidth;
    private PdfDocument myPdfDocument;
    private int totalpages = 1; // Andaikan 1 mukasurat pada mulanya

    public StudentScorePrintAdapter(Context context, List<StudentOverallScore> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras) {
        myPdfDocument = new PdfDocument();
        pageHeight = newAttributes.getMediaSize().getHeightMils() * 72 / 1000;
        pageWidth = newAttributes.getMediaSize().getWidthMils() * 72 / 1000;

        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }

        PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                .Builder("student_scores_report.pdf")
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .setPageCount(totalpages);

        PrintDocumentInfo info = builder.build();
        callback.onLayoutFinished(info, true);
    }

    @Override
    public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback) {
        PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
        PdfDocument.Page page = myPdfDocument.startPage(newPage);

        if (cancellationSignal.isCanceled()) {
            callback.onWriteCancelled();
            myPdfDocument.close();
            myPdfDocument = null;
            return;
        }

        drawPage(page);
        myPdfDocument.finishPage(page);

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

        // Tetapan "brush" untuk melukis
        Paint titlePaint = new Paint();
        titlePaint.setColor(Color.BLACK);
        titlePaint.setTextSize(20);
        titlePaint.setFakeBoldText(true);

        Paint subjectPaint = new Paint();
        subjectPaint.setColor(Color.BLACK);
        subjectPaint.setTextSize(16);
        subjectPaint.setFakeBoldText(true);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextSize(14);

        int leftMargin = 54;
        int topMargin = 54;
        int currentY = topMargin;

        // Lukis Tajuk Utama Laporan
        canvas.drawText("Student Scores Report", leftMargin, currentY, titlePaint);
        currentY += 40;

        // Loop melalui setiap pelajar
        for (StudentOverallScore student : studentList) {
            // Lukis nama pelajar
            canvas.drawText("Student: " + student.getStudentUsername(), leftMargin, currentY, subjectPaint);
            currentY += 25;

            // Loop melalui setiap subjek untuk pelajar ini
            for (SubjectScore subjectScore : student.getSubjectScores()) {
                canvas.drawText(subjectScore.getSubjectName(), leftMargin + 15, currentY, subjectPaint);
                currentY += 20;

                // Loop melalui setiap kuiz dalam subjek ini
                for (QuizScore quizScore : subjectScore.getQuizScores()) {
                    String scoreText = quizScore.getQuizName() + ": " + quizScore.getScore() + "/" + quizScore.getTotalQuestions();
                    canvas.drawText(scoreText, leftMargin + 30, currentY, textPaint);
                    currentY += 20;
                }
                currentY += 10; // Jarak antara subjek
            }
            currentY += 30; // Jarak besar antara setiap pelajar
        }

        // Lukis jumlah pelajar di bahagian bawah
        titlePaint.setTextSize(16);
        canvas.drawText("Total Students: " + studentList.size(), leftMargin, currentY, titlePaint);
    }
}
