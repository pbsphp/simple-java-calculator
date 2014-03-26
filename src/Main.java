class Main {
    public static void main(String[] args) {
        Parser parser = new Parser("2 + 76 - 4");

        System.out.println(parser.calculate());

        System.out.println((new Token("228")).toString());
    }
}
