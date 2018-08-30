import java.lang.Math;

abstract class FormaGeometrica {
    
    public int numLados;

    public abstract double calculaArea();
    public abstract void imprimeArea();
}
 
class Quadrado extends FormaGeometrica {

    public double lado;

    public Quadrado(double lado) {
        this.lado = lado;
        numLados = 4;
    }

    public double calculaArea() {
        return lado*lado;
    }

    public void imprimeArea() {
        System.out.println("Area do quadrado com lado " + lado + ": " + calculaArea());
    }

}

class Retangulo extends FormaGeometrica {

    public double base;
    public double altura;

    public Retangulo(double base, double altura) {
        numLados = 4;
        this.base = base;
        this.altura = altura;
    }

    public double calculaArea() {
        return base*altura;
    }

    public void imprimeArea() {
        System.out.println("Area do retângulo com base " + base + " e altura " + altura + ": " + calculaArea());
    }

}

class Circulo extends FormaGeometrica {

    public double raio;

    public Circulo(double raio) {
        this.raio = raio;
        numLados = 0;
    }

    public double calculaArea() {
        return Math.PI * raio * raio;
    }

    public void imprimeArea() {
        System.out.println("Area do circulo com raio " + raio + ": " + calculaArea());
    }

}

class Main {

    public static void main(String[] args) {

        System.out.println("Imprimindo um por um: ");
        Circulo circulo = new Circulo(5);
        circulo.imprimeArea();

        Quadrado quadrado = new Quadrado(5);
        quadrado.imprimeArea();

        Retangulo retangulo = new Retangulo(4, 6);
        retangulo.imprimeArea();

        /* Ou ainda usando polimorfismo para a impressão */
        System.out.println("\nImprimindo usando um foreach: ");
        FormaGeometrica formasGeometricas[] = new FormaGeometrica[3];
        formasGeometricas[0] = circulo;
        formasGeometricas[1] = quadrado;
        formasGeometricas[2] = retangulo;

        imprimeAreas(formasGeometricas);

        System.out.println("\nMudando as dimensões: ");
        circulo.raio = 6.64;
        quadrado.lado = 1.3;
        retangulo.base = 9;        

        imprimeAreas(formasGeometricas);

    }

    /* Método estático pois não depende de instância para ser executado */
    public static void imprimeAreas(FormaGeometrica formasGeometricas[]) {
        for (FormaGeometrica formaGeometrica : formasGeometricas) {
            formaGeometrica.imprimeArea();
        }
    }

}