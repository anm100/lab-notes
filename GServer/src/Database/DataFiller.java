package Database;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import Utils.DateTime;
import Views.Appointments;
import models.*;

public class DataFiller {
	//this

	public DataFiller(DbHandler d) {
		db = d;
	}

	DbHandler db;
	
}
