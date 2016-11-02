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

	DbHandler db;
	public static String[] firstNames = { "John", "Tyrion", "Arya", "Sandor", "Euron", "Dyneris", "Ned", "Cersi", "Sam",
			"Sansa", "Ramsey", "Roose", "Robert", "Walder", "Jora", "Varys", "Mountain", "Yara", "Jaime", "Stannis",
			"Renly", "Robert" };
	public static String[] lastNames = { "Blackfrey", "Martel", "River", "Andal", "Lanister", "Bolton", "Stark",
			"Clegane", "Targeryan", "Greyjoy", "Weiss", "Drogo", "Karstak", "Turlly", "Mormont", "Frey", "Baratheon",
			"Slavador", "Snow" };
	public static String[] cities = { "King's Landing", "Winterfell", "Iron Islands", "Valyeria", "The Wall" };

	public static String[] specialities = { "Surgon", "Oncologe", "Urologe", "Cardiologe", "Bone", "Family",
			"Children" };

	public static String[] eTypes = { "Blood", "Urine", "CT", "ECG", "X-Ray", "Eye", "CAT" };

	public static Random rand = new Random();

	public DataFiller(DbHandler d) {
		db = d;

	}

	public void fillManagers() throws SQLException {
		for (int i = 0; i < cities.length; i++) {
			Manager d = new Manager();
			d.setFirstName(firstNames[rand.nextInt(firstNames.length)]);
			d.setLastName(lastNames[rand.nextInt(lastNames.length)]);

			d.setEmail((d.getFirstName() + "." + d.getLastName() + i).toLowerCase() + "@crows.com");
			d.setBirthDate(Utils.DateTime.getDate(1960 + rand.nextInt(20), rand.nextInt(12), rand.nextInt(29)));
			d.setAddress(cities[rand.nextInt(cities.length)] + ", St. " + i);
			d.setPhone("0" + (548143001 + i));
			d.setClinic(db.clinics.queryForId(i % cities.length + 1));
			d.setPass("123123");
			d.setSid("" + (600000000 + i));
			d.setCeo(i == 0);
			db.managers.create(d);
		}

	}

	public void fillDoctors() throws SQLException {
		for (int i = 0; i < 15; i++) {
			Doctor d = new Doctor();
			d.setFirstName(firstNames[rand.nextInt(firstNames.length)]);
			d.setLastName(lastNames[rand.nextInt(lastNames.length)]);

			d.setEmail((d.getFirstName() + "." + d.getLastName() + i).toLowerCase() + "@crows.com");
			d.setBirthDate(Utils.DateTime.getDate(1960 + rand.nextInt(20), rand.nextInt(12), rand.nextInt(29)));
			d.setAddress(cities[rand.nextInt(cities.length)] + ", St. " + i);
			d.setPhone("0" + (548143001 + i));
			d.setClinic(db.clinics.queryForId(i % cities.length + 1));
			d.setPass("123123");
			d.setSpeciality(specialities[i % specialities.length]);
			d.setSid("" + (200000000 + i));
			db.doctors.create(d);
		}
	}

	public void fillPatients() throws Exception {
		for (int i = 0; i < 20; i++) {
			Patient p = new Patient();
			p.setFirstName(firstNames[rand.nextInt(firstNames.length)]);
			p.setLastName(lastNames[rand.nextInt(lastNames.length)]);

			p.setEmail((p.getFirstName() + "." + p.getLastName() + i).toLowerCase() + "@crows.com");
			p.setBirthDate(Utils.DateTime.getDate(1970 + rand.nextInt(20), rand.nextInt(12), rand.nextInt(29)));
			p.setAddress(cities[rand.nextInt(cities.length)] + ", St. " + i);
			p.setPhone("0" + (548143001 + i));
			p.setSid("" + (300000000 + i));
			p.setGender("male");
			p.setWeight(95);
			p.setHeight(175);
			db.patients.create(p);

			MedicalRecord md = new MedicalRecord();
			md.setPatient(p);
			md.setCreationDate(Utils.DateTime.randomDate());

			db.records.create(md);
			p.setMedicalRecord(md);
			db.patients.update(p);
	

		}
	}
	public void fillconfirmation() throws Exception {
		 int count=0; 
		 int j=0; 
		ArrayList <Patient> patients=  new ArrayList <Patient> (); 
		
		patients=(ArrayList<Patient>) db.patients.queryForAll();

		for (int i=0 ; i < patients.size(); i++ ){
			
			Patient p = patients.get(i);
			/// referral 1 
			Referral ref1 = new Referral();
			ref1.setPatient(p);
			ref1.setDoctor_name("ahmad");
			ref1.setSpeciality(specialities[count % specialities.length]);
			ref1.setDate(Utils.DateTime.currentDay());
			ref1.setActive(true);
			Confirmation conf = new Confirmation();
			conf.setRefferal_id("" + 50 + j);
			conf.setHmo_id("" + 100 + i);
			conf.setApproval_id("" + 50 + j);
			ref1.setConfirmation(conf);
			db.confirmations.create(conf);
			db.refferals.create(ref1);
			/// referral 2
			j++; 
			Referral ref2 = new Referral();
			ref2.setPatient(p);
			ref2.setDoctor_name("bolous");
			ref2.setSpeciality(specialities[(count+1) % specialities.length]);
			ref2.setDate(Utils.DateTime.randomDate());
			ref2.setActive(true);
			Confirmation conf2 = new Confirmation();
			conf2.setRefferal_id("" + 50 + j);
			conf2.setHmo_id("" + 100 + i);
			conf2.setApproval_id("" + 50 + j);
			ref2.setConfirmation(conf2);
			db.confirmations.create(conf2);
			db.refferals.create(ref2);
			/// referral 3
			j++;
			
			Referral ref3 = new Referral();
			ref3.setPatient(p);
			ref3.setDoctor_name("Muhmmad");
			ref3.setSpeciality(specialities[(count+2) % specialities.length]);
			ref3.setDate(Utils.DateTime.currentDay());
			ref3.setActive(true);
			Confirmation conf3 = new Confirmation();
			conf3.setRefferal_id("" + 50 + j);
			conf3.setHmo_id("" + 100 + i);
			conf3.setApproval_id("" + 50 + j);
			ref3.setConfirmation(conf3);
			db.confirmations.create(conf3);
			db.refferals.create(ref3);	
			j++;
			
			//count for next patient (specialty ...)
			count++; 
			
			
			
			
		}
		
	}
	public void fillStatistics() throws SQLException, ParseException {
		int i;
		Random r = new Random();
		Date d = Utils.DateTime.getDate(2016, 1, 1);
		for (i = 0; i < 360; i++) {
			Statistic s = new Statistic();
			s.setNumOfPatients(r.nextInt(30));
			s.setWaitingPeriod(r.nextInt(60));
			d = Utils.DateTime.addDay(d, 1);
			s.setDate(d);
			db.statistics.create(s);
		}
	}

	public void fillMedicalRecords() throws Exception {
		List<Doctor> doctors = db.doctors.queryForAll();
		List<Labratorian> labs = db.labratorians.queryForAll();

		for (MedicalRecord md : db.records.queryForAll()) {
			for (int i = 0; i < 3; i++) {
				Treatment t = new Treatment();
				t.setStart(DateTime.randomDate());
				t.settType("Patient is ok");
				t.setDoctor(doctors.get(rand.nextInt(doctors.size())));
				t.setMedicalRecord(md);
				db.treatments.create(t);
				fillExaminations(t, labs);
				fillVisits(t);
				db.records.update(md);
			}
		}
	}

	private void fillExaminations(Treatment t, List<Labratorian> labs) throws Exception {
		int randValue;
		for (int i = 0; i < rand.nextInt(3); i++) {
			Examination e = new Examination();
			e.setComments("This look good");
			e.seteType(eTypes[rand.nextInt(eTypes.length)]);
			e.setLabratorian(labs.get(rand.nextInt(labs.size())));
			e.setExaminationDate(DateTime.randomDate());
			e.setReferralDate(DateTime.getDate(e.getExaminationDate().getYear() + 1900,
					e.getExaminationDate().getMonth() + 1, e.getExaminationDate().getDay() - rand.nextInt(5)));
			e.setTreatment(t);
			randValue=rand.nextInt(2);
			if(randValue==0) e.setResults("good");
			Clinic clinic = db.clinics.queryForId(i+1);
			e.setClinic(clinic);
			db.examinations.create(e);
		}
	}

	private void fillVisits(Treatment t) throws Exception {

		for (int i = 0; i < rand.nextInt(3); i++) {
			Visit v = new Visit();
			v.setComments("The patient has a pain ");
			v.setTreatment(t);
			v.setVisitDate(Utils.DateTime.randomDate());
			db.visits.create(v);
		}
	}

	public void fillClinics() throws SQLException {
		for (int i = 0; i < cities.length; i++) {

			Clinic c = new Clinic();
			c.setAddress(cities[i]);
			c.setPhone("04-" + (5143001 + i));
			c.setName("GHealth " + c.getAddress());
			c.setEmail(c.getName().replace(" ", "_").toLowerCase() + i + "@crows.com");
			c.setHasLabratory((i % 2 == 0));
			db.clinics.createIfNotExists(c);
			if((i % 2 == 0))
			{
				

			Labratorian l = new Labratorian();
			l.setFirstName(firstNames[rand.nextInt(firstNames.length)]);
			l.setLastName(lastNames[rand.nextInt(lastNames.length)]);
			l.setPass("123123");
			l.setEmail((l.getFirstName() + "." + l.getLastName() + i).toLowerCase() + "@crows.com");
			l.setBirthDate(Utils.DateTime.getDate(1980 + rand.nextInt(20), rand.nextInt(12), rand.nextInt(29)));
			l.setAddress(cities[rand.nextInt(cities.length)] + ", St. " + i);
			l.setPhone("0" + (548143001 + i));
			l.setSid("" + (400000000 + i));
			l.setClinic(c);
			db.labratorians.create(l);
			}
			Secretary sec = new Secretary();
			sec.setFirstName(firstNames[rand.nextInt(firstNames.length)]);
			sec.setLastName(lastNames[rand.nextInt(lastNames.length)]);
			sec.setPass("123123");
			sec.setEmail((sec.getFirstName() + "." + sec.getLastName() + i).toLowerCase() + "@crows.com");
			sec.setBirthDate(Utils.DateTime.getDate(1980 + rand.nextInt(20), rand.nextInt(12), rand.nextInt(29)));

			sec.setClinic(c);
			sec.setAddress(cities[rand.nextInt(cities.length)] + ", St. " + i);
			sec.setPhone("0" + (548143001 + i));
			sec.setSid("" + (400000010 + i));
			db.secretaries.createIfNotExists(sec);
		}
	}

	public void fillDispatchers() throws SQLException {

		for (int i = 0; i < 3; i++) {
			Dispatcher dis = new Dispatcher();
			dis.setFirstName(firstNames[rand.nextInt(firstNames.length)]);
			dis.setLastName(lastNames[rand.nextInt(lastNames.length)]);
			dis.setPass("123123");
			dis.setEmail((dis.getFirstName() + "." + dis.getLastName() + i).toLowerCase() + "@crows.com");
			dis.setBirthDate(Utils.DateTime.getDate(1980 + rand.nextInt(20), rand.nextInt(12), rand.nextInt(29)));
			dis.setSid("" + (500000000 + i));
			dis.setAddress(cities[rand.nextInt(cities.length)] + ", St. " + i);
			dis.setPhone("0" + (548103004 + i));
			db.dispatchers.createIfNotExists(dis);
		}
	}

	public void fillAppointments() throws SQLException, ParseException {
		Date date;
		Appointments viewApp= new Appointments();
		Appointment app;
		boolean[] isDone={false,true,true,true,true};//with 80% to get true
		
		ArrayList<Patient> patients= (ArrayList<Patient>) db.patients.queryForAll();
		if(patients==null || patients.size()==0) return;
		
		for(Patient patient: patients){
			
			//get all referrals of this patient
			ArrayList<Referral> p_refs=(ArrayList<Referral>) db.refferals.queryForEq("patient_id", patient.getSid());
			if(p_refs==null || p_refs.size()==0) return;
			
			for(Referral ref:p_refs){
				String spec= ref.getSpeciality();
				ArrayList<Doctor> doc_by_spec=(ArrayList<Doctor>) db.doctors.queryForEq("speciality", spec);
				if(doc_by_spec==null || doc_by_spec.size()==0) return;
				
				
				
				date= DateTime.getDate(2016, rand.nextInt(5)+1, rand.nextInt(27)+1, rand.nextInt(8)+9, 00);
				if(viewApp.isExist(doc_by_spec.get(0).getSid(), patient.getSid(), date)==false
						&& DateTime.getDayOfWeek(date)!=6 && DateTime.getDayOfWeek(date)!=7){
					app= new Appointment(doc_by_spec.get(0), patient, date,DateTime.addDay(date, (-1)*rand.nextInt(20)));
					app.setDone(isDone[rand.nextInt(isDone.length)]);
					db.appointments.create(app);
				}
				
				date= DateTime.getDate(2016, rand.nextInt(5)+1, rand.nextInt(27)+1, rand.nextInt(8)+9, 00);
				if(viewApp.isExist(doc_by_spec.get(1).getSid(), patient.getSid(), date)==false
						&& DateTime.getDayOfWeek(date)!=6 && DateTime.getDayOfWeek(date)!=7){
					app= new Appointment(doc_by_spec.get(1), patient, date,DateTime.addDay(date, (-1)*rand.nextInt(20)));
					app.setDone(isDone[rand.nextInt(isDone.length)]);
					db.appointments.create(app);
				}
				
				int rand_doc=rand.nextInt(2);
				date= DateTime.getDate(2016, rand.nextInt(1)+6, rand.nextInt(15)+12, rand.nextInt(8)+9, 00);
				if(viewApp.isExist(doc_by_spec.get(rand_doc).getSid(), patient.getSid(), date)==false
						&& DateTime.getDayOfWeek(date)!=6 && DateTime.getDayOfWeek(date)!=7){
					app= new Appointment(doc_by_spec.get(rand_doc), patient, date,DateTime.addDay(date, (-1)*rand.nextInt(20)));
					app.setDone(false);
					db.appointments.create(app);
				}
			}
		}
	}

	public void fillShifts() throws SQLException, ParseException {

		
		ArrayList<Doctor> allDoctors= (ArrayList<Doctor>) db.doctors.queryForAll();
		if(allDoctors==null || allDoctors.size()==0) return;
		for(Doctor d : allDoctors){
			ArrayList<Shift> doc_shifts = doctorShiftsGenerator(2, d);
			for (Shift a : doc_shifts) {
				db.shifts.create(a);
			}
		}
	}

	private ArrayList<Shift> doctorShiftsGenerator(int weeks, Doctor d) throws ParseException {
		ArrayList<Shift> shifts = new ArrayList<Shift>();
		Date start_time = DateTime.getTime(9, 0);
		int from = DateTime.getWeekOfYear(start_time);
		Date end_time = DateTime.getTime(17, 0);
		for (int i = from; i < weeks + from; i++) {
			for (int j = 0; j < 5 && (DateTime.getDayOfWeek(start_time) != Calendar.FRIDAY)
					&& (DateTime.getDayOfWeek(start_time) != Calendar.SATURDAY); j++) {
				shifts.add(new Shift(start_time, end_time, d));
				start_time = DateTime.addDay(start_time, 1);
				end_time = DateTime.addDay(end_time, 1);
			}
			if (DateTime.getDayOfWeek(start_time) == Calendar.FRIDAY) {
				start_time = DateTime.addDay(start_time, 2);
				end_time = DateTime.addDay(end_time, 2);
			} else if (DateTime.getDayOfWeek(start_time) == Calendar.SATURDAY) {
				start_time = DateTime.addDay(start_time, 1);
				end_time = DateTime.addDay(end_time, 1);
			}
		}

		return shifts;
	}

}
