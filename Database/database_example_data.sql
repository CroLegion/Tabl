/*Creates a project*/
INSERT INTO jobs VALUES(1,"Build School",1,"Building HCR High School on South Stanton",NULL);
/*Creates 4 jobs*/
INSERT INTO jobs VALUES(2,"Foundation",2,"Lay Foundation for North section",1);
INSERT INTO jobs VALUES(3,"Framing",2,"Build wood framing struts",1);
INSERT INTO jobs VALUES(4,"Level Ground",2,"Level dirt for concrete foundation",2);
INSERT INTO jobs VALUES(5,"Cut Wood Lengths",2,"Cut proper lengths of 2x4s",3);
/*Creates Tasks for the jobs*/
INSERT INTO tasks VALUES(2,"Mix Concrete","Check label for mixing instructions. Using new mix.",2);
INSERT INTO tasks VALUES(3,"Turn on air compressor at 8am","New air compressor takes a while to turn on",3);
INSERT INTO tasks VALUES(4,"Make sure nail guns are full","This framing job takes 2 inch nails",3);
INSERT INTO tasks VALUES(5,"Place markings for stud distance",NULL,5);

/*Create Qualifications*/
INSERT INTO qualifications VALUES(1,"Electrical","Knows how to run wire through conduit, and maintain proper volatages, amperes");
INSERT INTO qualifications VALUES(2,"Cement","Knows how to mix conrete and lay sound foundations.");
INSERT INTO qualifications VALUES(3,"Plumbing","Knows how to run pipes through structures.");
INSERT INTO qualifications VALUES(4,"Truck Driver","Has CDL and able to run large trucks.");
INSERT INTO qualifications VALUES(5,"Roofing","Able to work on roofs with shingles.");

/*Assign Qualifications to User*/
INSERT INTO qualification_assignments VALUES(1,4352);
INSERT INTO qualification_assignments VALUES(2,4352);
INSERT INTO qualification_assignments VALUES(3,4352);

