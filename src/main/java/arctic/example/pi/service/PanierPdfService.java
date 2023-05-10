package arctic.example.pi.service;

import arctic.example.pi.entity.Panier;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;



@Service


public class PanierPdfService {
    public static ByteArrayInputStream PanierPDFReport(List<Panier> paniers) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Content to PDF file ->
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            Paragraph para = new Paragraph("Liste des paniers ", fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            // Add PDF Table Header ->
            Stream.of("Ref produit", "Prix Unité", "Quantity", "Prix Total").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                // header.setBackgroundColor(Color.RED);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });

            for (Panier panier : paniers) {
                PdfPCell refProduitCell = new PdfPCell(new Phrase(panier.getRef_product()));
                refProduitCell.setPaddingLeft(4);
                refProduitCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                refProduitCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(refProduitCell);


                PdfPCell PrixProductCell = new PdfPCell(new Phrase(String.valueOf(panier.getPrix_product())));
                PrixProductCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                PrixProductCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                PrixProductCell.setPaddingRight(4);
                table.addCell(PrixProductCell);

                PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(panier.getQuantity())));
                quantityCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantityCell.setPaddingRight(4);
                table.addCell(quantityCell);


                PdfPCell totalPanierCell = new PdfPCell(new Phrase(String.valueOf(panier.getTotal_panier())));
                totalPanierCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                totalPanierCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                totalPanierCell.setPaddingRight(4);
                table.addCell(totalPanierCell);
            }
            document.add(table);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
