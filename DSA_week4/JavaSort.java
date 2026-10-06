import java.util.*;

class Student{
    private int id;
    private String fname;
    private double cgpa;
    public Student(int id, String fname, double cgpa) {
        super();
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }
    public int getId() {
        return id;
    }
    public String getFname() {
        return fname;
    }
    public double getCgpa() {
        return cgpa;
    }
    public static int compareCGPA(Student st1,Student st2){
        if (st1.getCgpa()>st2.getCgpa()){return 0;}
        else if (st1.getCgpa()<st2.getCgpa()){return 1;}
        return 2;
    }
    public static int comparefname(Student st1,Student st2){
        if(st1.getFname().compareTo(st2.getFname())<0){return 0;}
        else if(st1.getFname().compareTo(st2.getFname())>0){return 1;}
        return 2;
    }
    public static int compareID(Student st1,Student st2){
        if(st1.getId()<st2.getId()){return 0;}
        return 1;
    }
}


class Solution
{
    public static void Sort(List<Student> studentsList){
        for(int i=0;i<studentsList.size();i++){
            for(int j=i+1;j<studentsList.size();j++){
                if (Student.compareCGPA(studentsList.get(i),studentsList.get(j))==1){
                    Student temp=studentsList.get(j);
                    studentsList.set(j, studentsList.get(i));
                    studentsList.set(i,temp);
                }
                else if(Student.compareCGPA(studentsList.get(i),studentsList.get(j))==2){
                    if(Student.comparefname(studentsList.get(i),studentsList.get(j))==1){
                        Student temp=studentsList.get(j);
                        studentsList.set(j, studentsList.get(i));
                        studentsList.set(i,temp);
                    }
                }
                else if(Student.compareCGPA(studentsList.get(i),studentsList.get(j))==2 &&
                        Student.comparefname(studentsList.get(i),studentsList.get(j))==2 ){
                    Student temp=studentsList.get(j);
                    studentsList.set(j, studentsList.get(i));
                    studentsList.set(i,temp);
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());

        List<Student> studentList = new ArrayList<Student>();
        while(testCases>0){
            int id = in.nextInt();
            String fname = in.next();
            double cgpa = in.nextDouble();

            Student st = new Student(id, fname, cgpa);
            studentList.add(st);

            testCases--;
        }
        Solution.Sort(studentList);
        for(Student st: studentList){
            System.out.println(st.getFname());
        }
    }
}



