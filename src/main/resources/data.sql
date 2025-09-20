-- Gifts
INSERT INTO Gift (giftId, giftName) VALUES (101, 'Free Minutes');
INSERT INTO Gift (giftId, giftName) VALUES (102, 'Extra Data');
INSERT INTO Gift (giftId, giftName) VALUES (103, 'Free Internet Bundles');
INSERT INTO Gift (giftId, giftName) VALUES (104, 'Discount Coupons & Vouchers');
INSERT INTO Gift (giftId, giftName) VALUES (105, 'Cash Back');
INSERT INTO Gift (giftId, giftName) VALUES (106, 'Special Offers');

-- Segments
INSERT INTO Segment (segmentId) VALUES (1);
INSERT INTO Segment (segmentId) VALUES (2);
INSERT INTO Segment (segmentId) VALUES (3);

-- SegmentGift
INSERT INTO SegmentGift (segmentId, giftId) VALUES (1, 101);
INSERT INTO SegmentGift (segmentId, giftId) VALUES (2, 102);
INSERT INTO SegmentGift (segmentId, giftId) VALUES (3, 103);
INSERT INTO SegmentGift (segmentId, giftId) VALUES (1, 104);
INSERT INTO SegmentGift (segmentId, giftId) VALUES (2, 105);
INSERT INTO SegmentGift (segmentId, giftId) VALUES (3, 106);

-- Customers
INSERT INTO Customer (id, msisdn, segmentId) VALUES (1, 123456789, 1);
INSERT INTO Customer (id, msisdn, segmentId) VALUES (2, 129876534, 2);
INSERT INTO Customer (id, msisdn, segmentId) VALUES (3, 123123123, 3);
