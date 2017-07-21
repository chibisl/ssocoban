<?xml version="1.0" encoding="UTF-8"?>
<tileset name="tiles" tilewidth="64" tileheight="64" tilecount="32" columns="16">
 <image source="tiles.png" width="1024" height="128"/>
 <tile id="13">
  <properties>
   <property name="type" value="exit"/>
  </properties>
  <objectgroup draworder="index">
   <properties>
    <property name="type" value="exit"/>
   </properties>
  </objectgroup>
  <animation>
   <frame tileid="13" duration="100"/>
   <frame tileid="29" duration="100"/>
   <frame tileid="28" duration="100"/>
  </animation>
 </tile>
 <tile id="17">
  <objectgroup draworder="index"/>
 </tile>
</tileset>
