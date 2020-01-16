package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidapplication_reto2.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDocumentFragment extends Fragment implements OnPageChangeListener, OnLoadCompleteListener,
        OnPageErrorListener {

    private File document;
    private PDFView pdfView;
    private View root;
    private int pageNumber;
    private String pdfFileName;

    public ViewDocumentFragment() {
        this.document=PrincipalUserFragment.getDocument();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_view_document, container, false);
        pdfView = root.findViewById(R.id.pdfViewer);
        loadDocument();
        return root;
    }

    private void loadDocument() {
        Uri uri = Uri.fromFile(document);
         pdfFileName = getFileName(uri);

        pdfView.fromFile(document)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(getContext()))
                .spacing(10) // in dp
                .onPageError(this)
                .load();
    }

    private String getFileName(Uri uri) {
            String result = null;
            if (uri.getScheme().equals("content")) {
                Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
            if (result == null) {
                result = uri.getLastPathSegment();
            }
            return result;

    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();

        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        //setTitle(new String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e("ViewDocumentFragment", String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }


    @Override
    public void onPageError(int page, Throwable t) {

    }
}
