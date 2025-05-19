package org.example;

import java.util.List;

public interface StudentsInterface <N, M> {

    List<N> findAll();
    N findById(M id);
    void deleteById(M id);
    void create(N n);
    void update(N n, M id);
}
