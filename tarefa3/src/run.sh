rm -rf bin
mkdir -p bin
find . -name "*.java" -not -path "./bin/*" | xargs javac -d bin
java -cp bin Main
