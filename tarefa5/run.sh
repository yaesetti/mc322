rm -rf bin
mkdir -p bin
find . -name "*.java" -not -path "./bin/*" | xargs javac --release 21 -d bin
java -cp bin game.Main
