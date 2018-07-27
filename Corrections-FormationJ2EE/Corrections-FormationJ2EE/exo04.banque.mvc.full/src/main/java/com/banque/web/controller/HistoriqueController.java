package com.banque.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.entity.IOperationEntity;
import com.banque.service.IOperationService;
import com.banque.service.impl.OperationService;
import com.banque.web.Clefs;
import com.banque.web.modele.HistoriqueBean;

/**
 * Controller pour l'historique <br/>
 */
@WebServlet(urlPatterns = "/ServletHistorique")
public class HistoriqueController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = 1L;

	// Nos services sont des objets 'stateless'
	private static final IOperationService SERVICE = new OperationService();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "comptes/historique.jsp";
		HistoriqueController.LOG.debug("--> Passage dans HistoriqueController.service");
		HistoriqueBean historiqueBean = new HistoriqueBean(request);
		// FIXME historiqueBean devrait etre valide
		request.setAttribute("hbean", historiqueBean);
		Integer utilisateurId = (Integer) request.getSession(true).getAttribute(Clefs.CLEF_AUTHENTIFICATION);
		try {
			HistoriqueController.LOG.debug(
					"-- HistoriqueController.service pour uid={} et cpid={} avec dateDebut={} dateFin={} credit={} debit={}",
					utilisateurId, historiqueBean.getCptId(), historiqueBean.getDateDebut(),
					historiqueBean.getDateFin(), historiqueBean.getCredit(), historiqueBean.getDebit());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			sdf.setLenient(false);
			Date dateDebut = null;
			if (historiqueBean.getDateDebut() != null) {
				dateDebut = sdf.parse(historiqueBean.getDateDebut());
			}
			Date dateFin = null;
			if (historiqueBean.getDateFin() != null) {
				dateFin = sdf.parse(historiqueBean.getDateFin());
			}
			if (dateDebut != null && dateFin == null) {
				dateFin = new Date(System.currentTimeMillis());
			}
			if (dateDebut == null && dateFin != null) {
				dateDebut = new Date(System.currentTimeMillis());
			}
			boolean credit = historiqueBean.getCredit() != null && historiqueBean.getCredit().booleanValue();
			boolean debit = historiqueBean.getDebit() != null && historiqueBean.getDebit().booleanValue();

			List<IOperationEntity> listeOperations = HistoriqueController.SERVICE.selectCritere(utilisateurId,
					historiqueBean.getCptId().intValue(), dateDebut, dateFin, credit, debit);
			HistoriqueController.LOG.debug("-- HistoriqueController.service pour uid={} et cpid={} resultat={}",
					utilisateurId, historiqueBean.getCptId(), Integer.valueOf(listeOperations.size()));
			request.setAttribute("listeOperations", listeOperations);
		} catch (Exception e) {
			request.setAttribute(Clefs.CLEF_ERREUR, e.getMessage());
			HistoriqueController.LOG.error("Erreur:", e);
		}
		this.forward(request, response, destination);
	}
}
