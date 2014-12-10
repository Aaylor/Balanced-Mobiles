ANT=$(shell which ant)
ANT_FILE=algorithm.xml

SRC_FOLDER=src
SRC_MAIN=$(SRC_FOLDER)/Main.java
OUTPUT=out/production/algorithm/

ifeq ($(ANT),)
	CC = javac -ea -cp $(SRC_FOLDER) $(SRC_MAIN) -d $(OUTPUT)
else
	CC = $(ANT) -f $(ANT_FILE)
endif

all:
	mkdir -p $(OUTPUT)
	$(CC)

clean:
	@ rm -rf out/*
