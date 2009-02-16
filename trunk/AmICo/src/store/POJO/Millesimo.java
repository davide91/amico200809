/**
 * 
 */
package store.POJO;

/**
 * @author bruno
 *
 */
public class Millesimo {

	private long id;
	private float quota;
	private UnitaImmobiliare quotaDi;
	private TabellaMillesimale tabellaMillesimale;
	
	public Millesimo()
	{
		
	}
	
	public Millesimo(float q,TabellaMillesimale tab)
	{
		tabellaMillesimale = tab;
		quota = q;
	}

	public float getQuota() {
		return quota;
	}

	public void setQuota(float quota) {
		this.quota = quota;
	}

	public UnitaImmobiliare getQuotaDi() {
		return quotaDi;
	}

	public void setQuotaDi(UnitaImmobiliare quotaDi) {
		this.quotaDi = quotaDi;
	}

	public TabellaMillesimale getTabellaMillesimale() {
		return tabellaMillesimale;
	}

	public void setTabellaMillesimale(TabellaMillesimale tabellaMillesimale) {
		this.tabellaMillesimale = tabellaMillesimale;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}