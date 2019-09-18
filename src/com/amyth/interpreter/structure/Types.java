package com.amyth.interpreter.structure;

public class Types {
    public  enum DataType {
        INT("integer"), STRING("string"), BOOL("boolean");

        private String repr;

        private DataType(String repr) {
            this.repr = repr;
        }

        @Override
        public String toString() { return repr; }
    };

    public enum RelationType{
        GR(">"), STRING("string"), BOOL("boolean");

        private String repr;

        private RelationType(String repr) {
            this.repr = repr;
        }

        @Override
        public String toString() { return repr; }
    }
}
