public class Db {
    public static  Student[] students;

    public static void save(){
        FileUtility.write(Db.students,"students.obj");
    }
    public static void initialize(){
        try {
            students=(Student[]) FileUtility.read("students.obj");
        }catch (Exception e){
            students=new Student[0];
        }
    }

}
