// Description: Java 25 edit object instance implementation for CFBam DelTopDep.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamDelTopDepEditObj
	extends CFBamDelDepEditObj

	implements ICFBamDelTopDepEditObj
{
	protected ICFBamTableObj requiredContainerTable;
	protected List<ICFBamDelSubDep1Obj> optionalComponentsDelDep;
	protected ICFBamDelTopDepObj optionalLookupPrev;
	protected ICFBamDelTopDepObj optionalLookupNext;

	public CFBamDelTopDepEditObj( ICFBamDelTopDepObj argOrig ) {
		super( argOrig );
		requiredContainerTable = null;
		optionalLookupPrev = null;
		optionalLookupNext = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getDelTopDepTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "DelTopDep" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamTableObj scope = getRequiredContainerTable();
		return( scope );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredName();
		return( objName );
	}

	@Override
	public ICFLibAnyObj getObjQualifier( Class qualifyingClass ) {
		ICFLibAnyObj container = this;
		if( qualifyingClass != null ) {
			while( container != null ) {
				if( container instanceof ICFBamClusterObj ) {
					break;
				}
				else if( container instanceof ICFBamTenantObj ) {
					break;
				}
				else if( qualifyingClass.isInstance( container ) ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		else {
			while( container != null ) {
				if( container instanceof ICFBamClusterObj ) {
					break;
				}
				else if( container instanceof ICFBamTenantObj ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		return( container );
	}

	@Override
	public ICFLibAnyObj getNamedObject( String objName ) {
		String nextName;
		String remainingName;
		ICFLibAnyObj subObj = null;
		ICFLibAnyObj retObj;
		int nextDot = objName.indexOf( '.' );
		if( nextDot >= 0 ) {
			nextName = objName.substring( 0, nextDot );
			remainingName = objName.substring( nextDot + 1 );
		}
		else {
			nextName = objName;
			remainingName = null;
		}
		if( remainingName == null ) {
			retObj = subObj;
		}
		else if( subObj == null ) {
			retObj = null;
		}
		else {
			retObj = subObj.getNamedObject( remainingName );
		}
		return( retObj );
	}

	@Override
	public String getObjQualifiedName() {
		String qualName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else if( container instanceof ICFBamSchemaDefObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				qualName = containerName + "." + qualName;
				container = container.getObjScope();
			}
		}
		return( qualName );
	}

	@Override
	public ICFBamScopeObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamDelTopDepObj retobj = getSchema().getDelTopDepTableObj().realiseDelTopDep( (ICFBamDelTopDepObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsDelTopDep().forget();
	}

	@Override
	public ICFBamDelTopDepObj moveUp() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUp" );
	}

	@Override
	public ICFBamDelTopDepObj moveDown() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDown" );
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamDelTopDepObj retobj = ((ICFBamSchemaObj)getOrigAsDelTopDep().getSchema()).getDelTopDepTableObj().createDelTopDep( getOrigAsDelTopDep() );
		if( retobj == getOrigAsDelTopDep() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getDelTopDepTableObj().updateDelTopDep( (ICFBamDelTopDepObj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getDelTopDepTableObj().deleteDelTopDep( getOrigAsDelTopDep() );
		return( null );
	}

	@Override
	public ICFBamDelTopDepTableObj getDelTopDepTable() {
		return( orig.getSchema().getDelTopDepTableObj() );
	}

	@Override
	public ICFBamDelTopDepEditObj getEditAsDelTopDep() {
		return( (ICFBamDelTopDepEditObj)this );
	}

	@Override
	public ICFBamDelTopDepObj getOrigAsDelTopDep() {
		return( (ICFBamDelTopDepObj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsDelTopDep().getSchema().getCFBamBackingStore().getFactoryDelTopDep().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerTable = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
		}
	}

	@Override
	public ICFBamDelTopDep getDelTopDepRec() {
		return( (ICFBamDelTopDep)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 id) {
		if (getPKey() != id) {
			setPKey(id);
		}
	}

	@Override
	public String getRequiredName() {
		return( getDelTopDepRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getDelTopDepRec().getRequiredName() != value ) {
			getDelTopDepRec().setRequiredName( value );
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( getDelTopDepRec().getRequiredTableId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalPrevId() {
		return( getDelTopDepRec().getOptionalPrevId() );
	}

	@Override
	public CFLibDbKeyHash256 getOptionalNextId() {
		return( getDelTopDepRec().getOptionalNextId() );
	}

	@Override
	public ICFBamTableObj getRequiredContainerTable() {
		return( getRequiredContainerTable( false ) );
	}

	@Override
	public ICFBamTableObj getRequiredContainerTable( boolean forceRead ) {
		if( forceRead || ( requiredContainerTable == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamTableObj obj = ((ICFBamSchemaObj)getOrigAsDelTopDep().getSchema()).getTableTableObj().readTableByIdIdx( getDelTopDepRec().getRequiredTableId() );
				requiredContainerTable = obj;
				if( obj != null ) {
					requiredContainerTable = obj;
				}
			}
		}
		return( requiredContainerTable );
	}

	@Override
	public void setRequiredContainerTable( ICFBamTableObj value ) {
		if( rec == null ) {
			getDelTopDepRec();
		}
		if( value != null ) {
			requiredContainerTable = value;
			getDelTopDepRec().setRequiredContainerTable(value.getTableRec());
		}
		requiredContainerTable = value;
	}

	@Override
	public List<ICFBamDelSubDep1Obj> getOptionalComponentsDelDep() {
		List<ICFBamDelSubDep1Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getDelSubDep1TableObj().readDelSubDep1ByDelTopDepIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamDelSubDep1Obj> getOptionalComponentsDelDep( boolean forceRead ) {
		List<ICFBamDelSubDep1Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getDelSubDep1TableObj().readDelSubDep1ByDelTopDepIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public ICFBamDelTopDepObj getOptionalLookupPrev() {
		return( getOptionalLookupPrev( false ) );
	}

	@Override
	public ICFBamDelTopDepObj getOptionalLookupPrev( boolean forceRead ) {
		if( forceRead || ( optionalLookupPrev == null ) ) {
			boolean anyMissing = false;
			if( getDelTopDepRec().getOptionalPrevId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamDelTopDepObj obj = ((ICFBamSchemaObj)getOrigAsDelTopDep().getSchema()).getDelTopDepTableObj().readDelTopDepByIdIdx( getDelTopDepRec().getOptionalPrevId() );
				optionalLookupPrev = obj;
			}
		}
		return( optionalLookupPrev );
	}

	@Override
	public void setOptionalLookupPrev( ICFBamDelTopDepObj value ) {
		if( rec == null ) {
			getDelTopDepRec();
		}
		if( value != null ) {
			optionalLookupPrev = value;
			getDelTopDepRec().setOptionalLookupPrev(value.getDelTopDepRec());
		}
		else {
			optionalLookupPrev = null;
			getDelTopDepRec().setOptionalLookupPrev((ICFBamDelTopDep)null);
		}
		optionalLookupPrev = value;
	}

	@Override
	public ICFBamDelTopDepObj getOptionalLookupNext() {
		return( getOptionalLookupNext( false ) );
	}

	@Override
	public ICFBamDelTopDepObj getOptionalLookupNext( boolean forceRead ) {
		if( forceRead || ( optionalLookupNext == null ) ) {
			boolean anyMissing = false;
			if( getDelTopDepRec().getOptionalNextId() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				ICFBamDelTopDepObj obj = ((ICFBamSchemaObj)getOrigAsDelTopDep().getSchema()).getDelTopDepTableObj().readDelTopDepByIdIdx( getDelTopDepRec().getOptionalNextId() );
				optionalLookupNext = obj;
			}
		}
		return( optionalLookupNext );
	}

	@Override
	public void setOptionalLookupNext( ICFBamDelTopDepObj value ) {
		if( rec == null ) {
			getDelTopDepRec();
		}
		if( value != null ) {
			optionalLookupNext = value;
			getDelTopDepRec().setOptionalLookupNext(value.getDelTopDepRec());
		}
		else {
			optionalLookupNext = null;
			getDelTopDepRec().setOptionalLookupNext((ICFBamDelTopDep)null);
		}
		optionalLookupNext = value;
	}

	@Override
	public void copyRecToOrig() {
		ICFBamDelTopDep origRec = getOrigAsDelTopDep().getDelTopDepRec();
		ICFBamDelTopDep myRec = getDelTopDepRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamDelTopDep origRec = getOrigAsDelTopDep().getDelTopDepRec();
		ICFBamDelTopDep myRec = getDelTopDepRec();
		myRec.set( origRec );
	}
}
