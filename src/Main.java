public class Main {


    public static void main(String[] args) {
        Db.initialize();
        while (true) {
            int menu = InputUtility.askInt("Emeliyyat icra etmek istediyiniz bolmeni secin: \n" +
                    "1.Muellimler \n" +
                    "2.Telebeler \n" +
                    "3.Cixis");


            ManagementService service = null;
            if (menu == 1) {
                service = new TeacherService();
            } else if (menu == 2) {
                service = new StudentService();
            } else if (menu == 3) {
                System.exit(0);
            } else {
                System.out.println("Duzgun secim etmediniz ");
            }

            if(menu>0 && menu<3){
                service.process();
            }

        }
    }
}