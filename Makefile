install:
	mvn clean install

run:
	mvn clean javafx:run

all: install run