# Digital Zoo Manager (Java Swing)

Aplikasi Java Swing untuk menambah hewan (Mammal/Bird) ke kebun binatang (`ArrayList<Animal>`). Hasil ditampilkan di log `JTextArea`.

## Struktur

```
DigitalZooManager/
└── src/
    ├── Animal.java
    ├── Mammal.java
    ├── Bird.java
    └── ZooFrame.java (berisi main())
```

Pakai package default (tanpa `package`) supaya mudah dibuka di IntelliJ IDEA.

## Cara Run

### Via terminal
```bash
javac -d out src/*.java
java -cp out ZooFrame
```

### Via IntelliJ IDEA
1. Open project folder `DigitalZooManager/`
2. Tandai `src/` sebagai Sources Root (biasanya otomatis)
3. Run `ZooFrame.main()`

## Cara Pakai
1. Isi Name dan Age
2. Pilih Type: `Generic Mammal` / `Generic Bird`
3. Jika Mammal → isi Fur Color. Jika Bird → centang Can Fly? bila bisa terbang.
4. Klik **Add Animal** → muncul di log, misal:
   `Added a new Mammal! Info: Name: Leo, Age: 5, Fur Color: Brown. Sound: The mammal makes a sound.`

## Requirement
- JDK 17+
