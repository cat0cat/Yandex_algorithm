package org.example;

import java.util.*;

public class B {

    public static void main(String[] args) {
        List<Discipline> disciplines = new ArrayList<>();
        List<Participant> participants = new ArrayList<>();
        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        //количество дисциплин
        short n = Short.parseShort(scanner.next());

        //дисциплины
        for (int i = 0; i < n; i++) {
            String vac = scanner.next();
            String[] arr = vac.split(",");
            Discipline discipline = new Discipline(arr[0], Short.parseShort(arr[1]));
            disciplines.add(discipline);
        }

        //число участников отборочного этапа соревнования
        int k = scanner.nextInt();

        //строковый идентификатор участника, название дисциплины, количество исполненных приёмов и начисленный ему штраф
        for (int i = 0; i < k; i++) {
            String res = scanner.next();
            String[] resArr = res.split(",");
            Participant participant = new Participant(
                    resArr[0],
                    resArr[1],
                    Integer.parseInt(resArr[2]),
                    Integer.parseInt(resArr[3]));
            participants.add(participant);
        }
        scanner.close();

        participants.sort(Collections.reverseOrder());

        for (Discipline v : disciplines) {
            String nameOfDiscipline = v.getName();
            short countOfParticipants = v.getMax();
            short count = 0;
            while (count < countOfParticipants) {
                for (Participant candidate : participants) {
                    if ((Objects.equals(candidate.getDiscipline(), nameOfDiscipline)) && (count < countOfParticipants)) {
                        result.add(candidate.getName());
                        count++;
                    }
                }
            }
        }

        Collections.sort(result);
        for (String r : result) {
            System.out.println(r);
        }
    }
}

class Discipline {
    private final String name;
    private final short max;

    public Discipline(String name, short max) {
        this.name = name;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public short getMax() {
        return max;
    }

}

class Participant implements Comparable<Participant> {
    private final String name;
    private final String discipline;
    private final int countMethod;
    private final int penalty;

    public Participant(String name, String discipline, int countMethod, int penalty) {
        this.name = name;
        this.discipline = discipline;
        this.countMethod = countMethod;
        this.penalty = penalty;
    }

    public String getName() {
        return name;
    }

    public String getDiscipline() {
        return discipline;
    }

    @Override
    public int compareTo(Participant o) {
        int d = discipline.compareTo(o.discipline);
        if (d != 0) return d;

        d = Integer.compare(countMethod, o.countMethod);
        if (d != 0) return d;

        return Integer.compare(-penalty, -o.penalty);
    }

}