package com.koko.it.entity;

import java.util.ArrayList;
import java.util.List;

public class PermissionTreeVo {
    public Long id;
    public String label;
    public boolean isSelect;
    public List<Children> children = new ArrayList<>();

    public static class Children{
        public Long id;
        public String label;
        public boolean isSelect;
    }
}
