package com.example.employee.Utils;

import java.util.Collections;
import java.util.Set;

public enum Role {
    USER(Collections.emptySet());

    <T> Role(Set<T> emptySet) {
    }
}
