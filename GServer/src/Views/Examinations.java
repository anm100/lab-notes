package Views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.j256.ormlite.stmt.QueryBuilder;
import models.Examination;
import models.Labratorian;
import Database.DbHandler;
import Server.Config;
import Utils.Request;

/**
 * Database view for visits , have all the visit Queries.
 * 
 * @author maisam marjieh
 *
 */
public class Examinations extends View {

	/**
	 * Query to add a new Examination to database
	 * 
	 * @param request
	 *            : "examinations/add" ,HashMap params: (examination).
	 * 
	 * @return success message if the Examination added successfully to dataBase
	 *         ,else return null
	 */
	public Object add(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		Examination ex = (Examination) request.getParam("exam");
		try {
			db.examinations.create(ex);
			return "success";
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}

	}

	/**
	 * get requested examinations from database and sent it to client
	 * 
	 * @param request
	 *            : "examinations/getById" ,HashMap params: (examination).
	 * @return the examinations instance
	 */

	public Object getById(Request request) {
		try {
			DbHandler db = Config.getConfig().getHandler();
			Examination ex = db.examinations.queryForId((Integer) request.getParam("sid"));
			return ex;
		} catch (SQLException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}

	/**
	 *
	 * get the image of examination
	 * 
	 * @param request
	 *            : "examinations/getExaminationImage" ,HashMap params:
	 *            (examination).
	 * @return image
	 * @throws SQLException
	 */

	public Object getExaminations(Request request) throws SQLException {
		DbHandler db = Config.getConfig().getHandler();

		Labratorian lab = (Labratorian) request.getParam("labratorian");

		ArrayList<Examination> examinations = (ArrayList<Examination>) db.examinations.queryForEq("clinic_id",
				lab.getClinic());
		return examinations;
	}

	public Object getExaminationImage(Request request) {
		Examination ex = (Examination) request.getParam("examination");
		ImageIcon image = new ImageIcon(Config.getConfig().getHomeDirectory() + "/examinations/" + ex.getFile());
		return image;
	}

	/**
	 * Query to update Examination add image and result of examination to
	 * database
	 * 
	 * @param request
	 *            : "examinations/update" ,HashMap params: (examination ,
	 *            image).
	 * @return success message if the examination was updated successfully
	 */
	public Object update(Request request) {
		DbHandler db = Config.getConfig().getHandler();
		try {

			Examination ex = (Examination) request.getParam("examination");

			if (request.getParam("image") != null) {
				Image image = ((ImageIcon) request.getParam("image")).getImage();

				ex.setFile(ex.getEid() + "-" + ex.getFile());
				File outputFile = new File(Config.getConfig().getHomeDirectory() + "/examinations/" + ex.getFile());

				BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null),
						BufferedImage.TYPE_INT_RGB);

				Graphics2D g2 = bi.createGraphics();
				g2.drawImage(image, 0, 0, null);
				g2.dispose();
				ImageIO.write(bi, "jpg", outputFile);
			}

			db.examinations.update(ex);
		} catch (SQLException | IOException e) {
			Config.getConfig().getLogger().exception(e);
		}
		return "success";
	}

}
