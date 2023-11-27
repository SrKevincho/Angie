package com.parcial.app.utilities;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.parcial.app.entity.Ticket;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component()
public class ListarTicketPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Obtiene la fecha actual
		Date fechaActual = new Date();

		// Formatea la fecha en un formato legible, por ejemplo, "dd/MM/yyyy"
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateada = sdf.format(fechaActual);

		// Establece el nombre del archivo PDF
		String fileName = "ReporteTickets.pdf"; // Cambia el nombre del archivo a lo que desees
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);

		@SuppressWarnings("unchecked")
		List<Ticket> listadoTickets = (List<Ticket>) model.get("tickets");

		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-10, -10, 40, 10);
		document.open();

		// Define un estilo de fuente personalizado para el encabezado de la empresa
		Font fuenteEmpresa = FontFactory.getFont("Helvetica", 12, Font.BOLD, Color.BLACK);
		fuenteEmpresa.setStyle(Font.UNDERLINE); // Agrega subrayado
		fuenteEmpresa.setFamily("Arial"); // Cambia la familia de la fuente
		fuenteEmpresa.setStyle(Font.ITALIC); // Agrega cursiva

		PdfPTable tablaEncabezado = new PdfPTable(2); // Cambia el número de columnas a 2 para dividir el título del
														// texto
		tablaEncabezado.setSpacingBefore(20); // Espacio antes de la tabla
		tablaEncabezado.setSpacingAfter(20); // Espacio después de la tabla
		tablaEncabezado.getDefaultCell().setBorder(Rectangle.BOX); // Agrega bordes a las celdas

		// Agrega la información de la empresa en la parte superior izquierda
		PdfPCell cellTitulo = new PdfPCell(new Phrase("Empresa:", fuenteEmpresa)); // Título
		cellTitulo.setBackgroundColor(new Color(51, 153, 255));  // Cambia el color a tu preferencia
		cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTitulo.setPadding(6);
		tablaEncabezado.addCell(cellTitulo);

		PdfPCell cellValor = new PdfPCell(new Phrase("TicketTechPro", fuenteEmpresa)); // Valor
		cellValor.setBackgroundColor(new Color(255, 255, 204));  // Cambia el color a tu preferencia
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellValor.setPadding(6);
		tablaEncabezado.addCell(cellValor);

		cellTitulo = new PdfPCell(new Phrase("Dirección:", fuenteEmpresa)); // Título
		cellTitulo.setBackgroundColor(new Color(51, 153, 255));  // Cambia el color a tu preferencia
		cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTitulo.setPadding(6);
		tablaEncabezado.addCell(cellTitulo);

		cellValor = new PdfPCell(new Phrase("123 Calle Principal, Ciudad", fuenteEmpresa)); // Valor
		cellValor.setBackgroundColor(new Color(255, 255, 204));  // Cambia el color a tu preferencia
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellValor.setPadding(6);
		tablaEncabezado.addCell(cellValor);

		cellTitulo = new PdfPCell(new Phrase("Contacto:", fuenteEmpresa)); // Título
		cellTitulo.setBackgroundColor(new Color(51, 153, 255));  // Cambia el color a tu preferencia
		cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTitulo.setPadding(6);
		tablaEncabezado.addCell(cellTitulo);

		cellValor = new PdfPCell(new Phrase("+123 456 789", fuenteEmpresa)); // Valor
		cellValor.setBackgroundColor(new Color(255, 255, 204));  // Cambia el color a tu preferencia
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellValor.setPadding(6);
		tablaEncabezado.addCell(cellValor);

		cellTitulo = new PdfPCell(new Phrase("Correo:", fuenteEmpresa)); // Título
		cellTitulo.setBackgroundColor(new Color(51, 153, 255));  // Cambia el color a tu preferencia
		cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTitulo.setPadding(6);
		tablaEncabezado.addCell(cellTitulo);

		cellValor = new PdfPCell(new Phrase("info@sistemadetickets.com", fuenteEmpresa)); // Valor
		cellValor.setBackgroundColor(new Color(255, 255, 204));  // Cambia el color a tu preferencia
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellValor.setPadding(6);
		tablaEncabezado.addCell(cellValor);

		cellTitulo = new PdfPCell(new Phrase("Fecha:", fuenteEmpresa)); // Título
		cellTitulo.setBackgroundColor(new Color(51, 153, 255));  // Cambia el color a tu preferencia
		cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTitulo.setPadding(6);
		tablaEncabezado.addCell(cellTitulo);

		cellValor = new PdfPCell(new Phrase(fechaFormateada, fuenteEmpresa)); // Valor
		cellValor.setBackgroundColor(new Color(255, 255, 204));  // Cambia el color a tu preferencia
		cellValor.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValor.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellValor.setPadding(6);
		tablaEncabezado.addCell(cellValor);
		
		document.add(tablaEncabezado);

		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;

		Font fuenteTitulo = FontFactory.getFont("COURIER", 20, Color.WHITE);

		celda = new PdfPCell(new Phrase(" Registro de Tickets ", fuenteTitulo));
		fuenteTitulo.setStyle(Font.BOLD);
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(51, 102, 255));  // Fondo azul
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celda.setPadding(30);
		celda.setPhrase(new Phrase(" Registro de Tickets ", FontFactory.getFont("Arial", 12, Font.BOLD, Color.BLACK))); // Texto en negro




		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);

		PdfPTable tablaClientes = new PdfPTable(7);

		Font fuenteCeldas = FontFactory.getFont("VERDANA", 12); // Define una fuente más pequeña
		Font fuenteTitulos = FontFactory.getFont("VERDANA", 12, Color.BLACK); fuenteTitulos.setStyle(Font.BOLD);// Fuente para los títulos

		// Agrega los títulos de las columnas centrados
		PdfPCell cell = new PdfPCell(new Phrase("ID", fuenteTitulos));

		cell = new PdfPCell(new Phrase("Título", FontFactory.getFont("Arial", 12, Font.BOLD, Color.BLACK)));
		cell.setPadding(10);
		cell.setBackgroundColor(new Color(51, 102, 255));  // Fondo azul
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tablaClientes.addCell(cell);

		cell = new PdfPCell(new Phrase("Descripción", FontFactory.getFont("Arial", 12, Font.BOLD, Color.BLACK)));
		cell.setPadding(10);
		cell.setBackgroundColor(new Color(51, 102, 255));  // Fondo azul
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tablaClientes.addCell(cell);

		cell = new PdfPCell(new Phrase("Prioridad", FontFactory.getFont("Arial", 12, Font.BOLD, Color.BLACK)));
		cell.setPadding(10);
		cell.setBackgroundColor(new Color(51, 102, 255));  // Fondo azul
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tablaClientes.addCell(cell);

		cell = new PdfPCell(new Phrase("Estado", FontFactory.getFont("Arial", 12, Font.BOLD, Color.BLACK)));
		cell.setPadding(10);
		cell.setBackgroundColor(new Color(51, 102, 255));  // Fondo azul
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tablaClientes.addCell(cell);

		cell = new PdfPCell(new Phrase("Categoría", FontFactory.getFont("Arial", 12, Font.BOLD, Color.BLACK)));
		cell.setPadding(10);
		cell.setBackgroundColor(new Color(51, 102, 255));  // Fondo azul
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tablaClientes.addCell(cell);

		cell = new PdfPCell(new Phrase("Asignado", FontFactory.getFont("Arial", 12, Font.BOLD, Color.BLACK)));
		cell.setPadding(10);
		cell.setBackgroundColor(new Color(51, 102, 255));  // Fondo azul
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tablaClientes.addCell(cell);

		cell = new PdfPCell(new Phrase("Comentarios", FontFactory.getFont("Arial", 12, Font.BOLD, Color.BLACK)));
		cell.setPadding(10);
		cell.setBackgroundColor(new Color(51, 102, 255));  // Fondo azul
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tablaClientes.addCell(cell);


		listadoTickets.forEach(ticket -> {
			PdfPCell cell2 = new PdfPCell(new Phrase(ticket.getId(), fuenteCeldas));

			cell2 = new PdfPCell(new Phrase(ticket.getTitle(), fuenteCeldas));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setPadding(5); // Establece el relleno interno
			tablaClientes.addCell(cell2);

			cell2 = new PdfPCell(new Phrase(ticket.getDescription(), fuenteCeldas));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setPadding(5); // Establece el relleno interno
			tablaClientes.addCell(cell2);
			
			cell2 = new PdfPCell(new Phrase(ticket.getPriority(), fuenteCeldas));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setPadding(5); // Establece el relleno interno
			tablaClientes.addCell(cell2);
			
			cell2 = new PdfPCell(new Phrase(ticket.getTicketStatus(), fuenteCeldas));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setPadding(5); // Establece el relleno interno
			tablaClientes.addCell(cell2);
			
			cell2 = new PdfPCell(new Phrase(ticket.getCategory(), fuenteCeldas));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setPadding(5); // Establece el relleno interno
			tablaClientes.addCell(cell2);
			
			cell2 = new PdfPCell(new Phrase(ticket.getAssignedTo(), fuenteCeldas));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setPadding(5); // Establece el relleno interno
			tablaClientes.addCell(cell2);
			
			cell2 = new PdfPCell(new Phrase(ticket.getComments(), fuenteCeldas));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setPadding(5); // Establece el relleno interno
			tablaClientes.addCell(cell2);

		});

		document.add(tablaTitulo);
		document.add(tablaClientes);

	}

}
