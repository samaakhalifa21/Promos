
CREATE TABLE Gift (
    giftId INTEGER PRIMARY KEY,
    giftName VARCHAR(255) NOT NULL
);

CREATE TABLE Segment (
    segmentId INTEGER PRIMARY KEY
);

CREATE TABLE SegmentGift (
    segmentId INTEGER,
    giftId INTEGER,
    PRIMARY KEY (segmentId, giftId),
    FOREIGN KEY (segmentId) REFERENCES Segment(segmentId),
    FOREIGN KEY (giftId) REFERENCES Gift(giftId)
);

CREATE TABLE Customer (
    id INTEGER PRIMARY KEY,
    msisdn BIGINT NOT NULL,
    segmentId INTEGER,
    FOREIGN KEY (segmentId) REFERENCES Segment(segmentId)
);
CREATE TABLE HISTORY (
    id INT AUTO_INCREMENT PRIMARY KEY,
    msisdn BIGINT NOT NULL,
    giftId INT NOT NULL,
    usedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (giftId) REFERENCES GIFT(giftId)
);
