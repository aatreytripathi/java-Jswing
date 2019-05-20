-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.18-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema projectdb
--

CREATE DATABASE IF NOT EXISTS projectdb;
USE projectdb;

--
-- Definition of table `accountinfo`
--

DROP TABLE IF EXISTS `accountinfo`;
CREATE TABLE `accountinfo` (
  `Userid` varchar(20) NOT NULL,
  `Userpass` varchar(30) NOT NULL,
  `Usertype` varchar(20) NOT NULL,
  `Useremail` varchar(45) NOT NULL,
  PRIMARY KEY  (`Userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accountinfo`
--

/*!40000 ALTER TABLE `accountinfo` DISABLE KEYS */;
INSERT INTO `accountinfo` (`Userid`,`Userpass`,`Usertype`,`Useremail`) VALUES 
 ('aatrey','Tripathi','clerk','aatrey97@gmail.com'),
 ('ashish','tripathi','clerk','ashish.tripathi@gmail.com'),
 ('jaiswal','gautam','admin','gautamjaiswal97@gmail.com');
/*!40000 ALTER TABLE `accountinfo` ENABLE KEYS */;


--
-- Definition of table `memberinfo`
--

DROP TABLE IF EXISTS `memberinfo`;
CREATE TABLE `memberinfo` (
  `memberid` varchar(20) NOT NULL,
  `membername` varchar(50) NOT NULL,
  `email` varchar(30) NOT NULL,
  `gender` varchar(6) default NULL,
  `address` varchar(100) NOT NULL,
  `phoneno` varchar(20) NOT NULL,
  `Dob` date NOT NULL,
  `Occupation` varchar(50) NOT NULL,
  `planid` varchar(20) NOT NULL,
  `dateofmembership` date NOT NULL,
  `dateofexpiry` date NOT NULL,
  PRIMARY KEY  USING BTREE (`memberid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `memberinfo`
--

/*!40000 ALTER TABLE `memberinfo` DISABLE KEYS */;
INSERT INTO `memberinfo` (`memberid`,`membername`,`email`,`gender`,`address`,`phoneno`,`Dob`,`Occupation`,`planid`,`dateofmembership`,`dateofexpiry`) VALUES 
 ('Aatrey96','Aatrey Tripathi','aatrey.tripathi@gmail.com','Male','Near Bhootnath Market , Lucknow','+919876543219','1997-07-09','Student','Summer_June','2018-07-10','2018-11-07'),
 ('aatrey97','aatrey gupta','gupta@gmail.com','Male','Aminabad , Lucknow','+919987654321','1997-07-21','student','weekend','2018-07-09','2018-07-19'),
 ('Gauti97','Gautam Jaiswal','gautamjaiswal97@gmail.com','Male','Model House , Lucknow	','+919125234989','1997-06-16','Student','weekend','1997-06-16','2018-08-06'),
 ('pari97','Parmangini','pari97@gmail.com','Female','Near Bhootnath Market ,Lucknow','+919899765431','1997-07-16','student','Winter_Jan','2017-08-07','2018-08-07'),
 ('siyaram@','Sita ','siyaram@yahoo.com','Female','Gomti Nagar','+919919325735','2006-07-08','Student','abc','2018-07-28','2018-08-07');
/*!40000 ALTER TABLE `memberinfo` ENABLE KEYS */;


--
-- Definition of table `planinfo`
--

DROP TABLE IF EXISTS `planinfo`;
CREATE TABLE `planinfo` (
  `planid` varchar(20) NOT NULL,
  `planname` varchar(50) NOT NULL,
  `facilities` varchar(100) default NULL,
  `charge` varchar(50) NOT NULL,
  `duration` varchar(50) NOT NULL,
  PRIMARY KEY  (`planid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `planinfo`
--

/*!40000 ALTER TABLE `planinfo` DISABLE KEYS */;
INSERT INTO `planinfo` (`planid`,`planname`,`facilities`,`charge`,`duration`) VALUES 
 ('Summer_June','Short-term_Course','5 days Practise /week','11000','120'),
 ('weekend','One_week_Plan','Only Training','2000','10'),
 ('Winter_Jan','Full-year_Course','6 -days Practise /week','30000','365'),
 ('Winter_Sept.','Crack_Course','4 days Practise /week','15000','120');
/*!40000 ALTER TABLE `planinfo` ENABLE KEYS */;


--
-- Definition of table `renew`
--

DROP TABLE IF EXISTS `renew`;
CREATE TABLE `renew` (
  `memberid` varchar(20) NOT NULL,
  `planid` varchar(20) NOT NULL,
  `dateofmembership` date NOT NULL,
  `dateofexpiry` date NOT NULL,
  KEY `FK_renew_1` (`memberid`),
  CONSTRAINT `FK_renew_1` FOREIGN KEY (`memberid`) REFERENCES `memberinfo` (`memberid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `renew`
--

/*!40000 ALTER TABLE `renew` DISABLE KEYS */;
INSERT INTO `renew` (`memberid`,`planid`,`dateofmembership`,`dateofexpiry`) VALUES 
 ('siyaram@','abc','2018-07-28','2018-08-07'),
 ('aatrey97','weekend','2018-07-09','2018-07-19'),
 ('Aatrey96','Summer_June','2018-07-10','2018-11-07');
/*!40000 ALTER TABLE `renew` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
