#SIMPLE K-MEANS CLUSTERING USING DATA FROM :http://archive.ics.uci.edu

data <- read.table('http://archive.ics.uci.edu/ml/machine-learning-databases/wine/wine.data',
                   header=F,
                   sep=',')
names(data) <- c('Class',
                 'Alcohol',
                 'Malic acid',
                 'Ash',
                 'Alcalinity of ash',
                 'Magnesium',
                 'Total phenols',
                 'Flavanoids',
                 'Nonflavanoid phenols',
                 'Proanthocyanins',
                 'Color intensity',
                 'Hue',
                 'OD280/OD315 of diluted wines',
                 'Proline')


data2 <- scale(data[-1]) 

#used in identifying the optimal number of clusters for a given dat set 
Clusterddata <- NbClust(data2, min.nc=2, max.nc=15, method="kmeans")
table(Clusterddata$Best.n[1,])


#seeding 
set.seed(123)

#clusterng itself
data.km<- kmeans(data2,3, iter.max = 30, nstart = 88)
#identifying the centres
data.km$centers


  