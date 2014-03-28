

colidata <- read.table('http://archive.ics.uci.edu/ml/machine-learning-databases/ecoli/ecoli.data',
                       header=F)

names(colidata) <- c('A',
                     'B',
                     'C',
                     'D',
                     'E',
                     'F',
                     'G',
                     'H',
                     'I'
)

#IDENTIFYING THE CATEGORY OF ECOLI
set.seed(4356)
ecolidata<-as.matrix(colidata)
ecoli.rpart<-rpart(A ~ B + C + E + F + G , colidata)
plotcp(ecoli.rpart)
printcp(ecoli.rpart)

plotcp(ecoli.rpart)
printcp(ecoli.rpart)
plot(ecoli.rpart, uniform=TRUE )