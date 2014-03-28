class Main {
    public static void main(String[] args) {
        Parser parser = new Parser("228 + 28 - 56 - 100");

        // System.out.println((new Token("228")).toString());

        System.out.println(parser.calculate());

    }
}
