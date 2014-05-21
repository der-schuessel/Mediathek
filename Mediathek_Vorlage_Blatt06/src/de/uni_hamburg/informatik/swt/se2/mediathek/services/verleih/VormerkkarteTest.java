package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Geldbetrag;
import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

public class VormerkkarteTest
{
    private Kunde _kunden[];
    private Medium _medium;
    private int _anzahl;
    private Vormerkkarte _karte;
    private Kunde _kunde;

    public VormerkkarteTest()
    {
        _kunden = new Kunde[3];
        _anzahl = 0;
        _medium = new CD("bar", "baz", "foo", 123);
        _kunde = new Kunde(new Kundennummer(123456), "ich", "du");
        _karte = new Vormerkkarte(_kunde, _medium);
    }

    @Test
    public void testeKonstruktor() throws Exception
    {
        assertEquals(_kunde, _karte.gibVormerker(0));
        assertEquals(_medium, _karte.gibMedium());
    }


    @Test
    public void testEquals()
    {
        Vormerkkarte karte1 = new Vormerkkarte(_kunde, _medium);

        assertFalse(_karte.equals(karte1));
        assertNotSame(_karte.hashCode(), karte1.hashCode());

        Kunde kunde2 = new Kunde(new Kundennummer(654321), "ich", "du");
        CD medium2 = new CD("hallo", "welt", "foo", 321);
        Vormerkkarte karte2 = new Vormerkkarte(kunde2, medium2);

        assertFalse(_karte.equals(karte2));
        assertNotSame(_karte.hashCode(), karte2.hashCode());

    }
    
    @Test
    public void testgibAnzahlVormerker()
    {
        assertEquals(1,_karte.gibAnzahlVormerker());
        _karte.vormerkerHinzufuegen(new Kunde(new Kundennummer(645879), "er", "sie"));
        _karte.vormerkerHinzufuegen(new Kunde(new Kundennummer(646879), "wir", "sie"));
        assertEquals(3, _karte.gibAnzahlVormerker());
        _karte.vormerkerHinzufuegen(new Kunde(new Kundennummer(649979), "ich", "wir"));
        assertEquals(3, _karte.gibAnzahlVormerker());
    }
    
    @Test
    public void testGibVormerker()
    {
        assertEquals(_kunde, _karte.gibVormerker(0));
        assertNotSame(_kunde, _karte.gibVormerker(1));
        Kunde kunde2 = new Kunde(new Kundennummer(649979), "ich", "wir");
        _karte.vormerkerHinzufuegen(kunde2);
        assertEquals(kunde2, _karte.gibVormerker(1));
        _karte.vormerkerHinzufuegen(kunde2);
        assertNotSame(kunde2, _karte.gibVormerker(2));
    }
    
    @Test
    public void testVormerkerHinzufügen()
    {
        Kunde kunde2 = new Kunde(new Kundennummer(649979), "ich", "wir");
        _karte.vormerkerHinzufuegen(kunde2); 
        assertEquals(kunde2, _karte.gibVormerker(1));
        _karte.vormerkerHinzufuegen(kunde2);
        assertEquals(null, _karte.gibVormerker(2));
    }
    
    @Test
    public void testEntferneVormerker()
    {
        Kunde kunde2 = new Kunde(new Kundennummer(649979), "ich", "wir");
        _karte.vormerkerHinzufuegen(kunde2); 
        assertEquals(kunde2, _karte.gibVormerker(1));
        _karte.entferneVormerker(_kunde);
        assertFalse(_kunde.equals(_karte.gibVormerker(0)));
        assertEquals(kunde2, _karte.gibVormerker(0));
        assertFalse(kunde2.equals(_karte.gibVormerker(1)));
    }
}
