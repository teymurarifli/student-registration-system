public class StudentService extends ManagementService {
    public  void process() {
        int studentMenu = InputUtility.askInt("Icra etmek istediyiniz emeliyyati secin: \n" +
                "1.Qeydiyyat \n" +
                "2.Axtaris \n" +
                "3.Silmek \n" +
                "4.Melumati deyismek \n" +
                "5.Siyahini gostermek \n" );

        if(studentMenu==1){
            register();
            Db.save();
        } else if (studentMenu==2) {
            search();
        } else if (studentMenu==3) {
            delete();
            Db.save();
        } else if (studentMenu==4) {
            update();
            Db.save();
        } else if (studentMenu==5) {
            showAll();
        }else {
            System.out.println("Duzgun secim etmediniz : ");
        }
    }
    public void update(){
        search();

        int studentNumber = InputUtility.askInt("Ad ve ya soyad daxil edin");

        Student student = Db.students[studentNumber];

        while(true){
            String field = InputUtility.askString("Hansi melumati deyismek isteyirsiniz?");
            if(field.equalsIgnoreCase("name")){
                student.setName(InputUtility.askString("Telebenin adini daxil edin: "));
            } else if (field.equalsIgnoreCase("surname")) {
                student.setSurname(InputUtility.askString("Telebenin soyadini daxil edin: "));
            } else if (field.equalsIgnoreCase("age")) {
                student.setAge(InputUtility.askInt("Telebenin yasini daxil edin"));
            } else if (field.equalsIgnoreCase("scholarship")) {
                student.setScholarship(InputUtility.askDouble("Telebenin teqaudunu daxil edin"));
            } else if (field.equalsIgnoreCase("university")) {
                University university = new University();
                university.setName(InputUtility.askString("Telebenin universitetini daxil edin: "));
                student.setUniversity(university);
            } else if (field.equalsIgnoreCase("done"))  {
                break;
            }
        }

    }

    public void register(){
        int registerCount = InputUtility.askInt("Qeydiyyata almaq istediyiniz telebe sayini daxil edin: ");
        Student[] oldStudents= Db.students;
        Student[] newStudents= new Student[registerCount];

        for(int i =0;i<registerCount;i++){
            newStudents[i] = prepareStudent();
        }
        Student[]result=new Student[oldStudents.length+newStudents.length];
        for (int i=0;i< oldStudents.length;i++){
            result[i]=oldStudents[i];
        }
        for (int i=0;i< newStudents.length;i++){
            result[oldStudents.length+i]=newStudents[i];
        }
        Db.students=result;
    }

    public void search(){
        String search = InputUtility.askString("Ad ve ya soyad daxil edin: ");
        for(int i =0;i< Db.students.length;i++){
            Student student = Db.students[i];
            if (StringUtility.containsIgnoreCase(student.getName(),search) ||
            StringUtility.containsIgnoreCase(student.getSurname(),search)){
                System.out.println(i+"."+student);
            }
        }
    }

    public void delete(){
        search();

        int studentNumber = InputUtility.askInt("Ad ve ya soyad daxil edin");

        Db.students[studentNumber] = null;
        Student[] newStudents = new Student[Db.students.length-1];
        int j = 0;
        for (int i = 0;i<Db.students.length;i++){
            if(Db.students[i] != null){
                newStudents[j] = Db.students[i];
                j++;
            }
        }
        Db.students = newStudents;
    }



    public void showAll(){
        for(int i =0;i< Db.students.length;i++){
            System.out.println(i+"."+Db.students[i]);
        }
    }

    private static Student prepareStudent(){
        Student student = new Student();

        student.setName(InputUtility.askString("Telebenin adini daxil edin: "));
        student.setSurname(InputUtility.askString("Telebenin soyadini daxil edin: "));
        student.setAge(InputUtility.askInt("Telebenin yasini daxil edin"));
        student.setScholarship(InputUtility.askDouble("Telebenin teqaudunu daxil edin"));

        University university = new University();
        university.setName(InputUtility.askString("Telebenin universitetini daxil edin: "));


        student.setUniversity(university);
        return student;
    }
}
