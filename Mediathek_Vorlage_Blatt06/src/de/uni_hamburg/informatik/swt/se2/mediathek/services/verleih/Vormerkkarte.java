package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;


public class Vormerkkarte
{
    private Kunde[] _vormerker = new Kunde[3];
    private final Medium _medium;
    private int _anzahl;
    private static final int MAX_ANZAHL = 3;
    
    /**
     * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
     * 
     * @param _vormerker Ein Array von Kunden mit einer zu definierenden maximalen Anzahl.
     * @param medium Das vorgemerkte Medium.
     * 
     * @require kunde != null
     * @require medium != null
     * 
     * 
     * @ensure #gibVormerker(kunde) == _vormerker[_anzahl];
     * @ensure #gibMedium() == medium
     * @ensure #gibAnzahlVormerker() == 1
     */
    public Vormerkkarte(Kunde kunde, Medium medium)
    {
        assert kunde != null : "Vorbedingung verletzt: entleiher != null";
        assert medium != null : "Vorbedingung verletzt: medium != null";
                
        _vormerker[0] = kunde;
        _medium = medium;
        _anzahl = 1;
    }
    
    
    /**
     * Gib die Anzahl an Vormerkern zurück.
     * 
     
     * @require _anzahl != null
     *    
     */
    public int gibAnzahlVormerker(){
      
       return _anzahl;
    }
    
    /**
     * Gibt den Vormerker mit bestimmten Index zurück 
     * 
     * 
     * @param index Index der Vormerker-Position.
     * 
     * @require _vormerker != null
     * @require index != null
     */
    public Kunde gibVormerker(int index)
    {
       
        assert _anzahl != 0 : "Vorbedingung verletzt: gibAnzahl() !=null";
        assert _vormerker != null : "Vorbedingung verletzt: _vormerker !=null";
        return _vormerker[index];
    }
    
    /**
     * Gibt das Medium zur Vormerkkarte zurück 
     * 
     * 
     * @require _medium != null
     * 
     */
    public Medium gibMedium()
    {
        assert _medium != null : "Vorbedingung verletzt: _medium !=null";
        return _medium;
    }
    
    
    /**
     * Gibt den Vormerker mit bestimmten Index zurück 
     * 
     *
     * 
     * @require _vormerker != null
     * 
     */
    public Kunde[] gibAlleVormerker()
    {
        
        return _vormerker;
    }
    
    public boolean entferneVormerker(Kunde kunde)
    {
        switch(_anzahl){
        case(0):
            return false;
            
        case(1):
            _anzahl = 0;
            return true;
            
        case(MAX_ANZAHL):
            _vormerker[_anzahl-1] = null;
            _anzahl--;
        return true;
        default:
            int index = 0;
            for (int i = 0; i < _anzahl; i++)
            {
                if (kunde.equals(_vormerker[i]))
                {
                    index = i;
                    System.out.println(index);
                } 
            }
            for (int i = index; i < _anzahl-1; i++)
            {
                System.out.println(_vormerker[i]);
                _vormerker[i] = _vormerker[i+1];
                _vormerker[i+1] = null;
            }
            _anzahl--;
            return true;
            
        }
    }  

    /**
     * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
     * 
     * @param kunde Kunden wird als Vormerker aufgelistet
     *
     * 
     * @require kunde != null
     *  
     * 
     * 
     */
    public boolean vormerkerHinzufuegen(Kunde kunde)
    {
        for (Kunde vormerker : _vormerker)
        {
            if (kunde.equals(vormerker)) {
                return false;
            }
        }
        
        if (_anzahl < MAX_ANZAHL)
        {_vormerker[_anzahl] = kunde;
        _anzahl++;
        return true;
        }
        
        return false;
    }
   

}
