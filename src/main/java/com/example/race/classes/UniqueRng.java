package com.example.race.classes;

import java.util.*;

public class UniqueRng implements Iterator<Integer> {
    private List<Integer> numbers = new ArrayList<>();

    public UniqueRng(int n) {
        List<Integer> list=new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(list.size());
            numbers.add(list.get(index));
            list.remove(index);
        }
        for (Integer integer : numbers) {
            System.out.print(integer+" ");
        }
    }



    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers.remove(0);
    }

    @Override
    public boolean hasNext() {
        return !numbers.isEmpty();
    }


}