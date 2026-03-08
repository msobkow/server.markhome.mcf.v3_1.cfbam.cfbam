// Description: Java 25 edit object instance implementation for CFBam PopTopDep.

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

public class CFBamPopTopDepEditObj
	extends CFBamPopDepEditObj

	implements ICFBamPopTopDepEditObj
{
	protected ICFBamRelationObj requiredContainerContRelation;
	protected List<ICFBamPopSubDep1Obj> optionalComponentsPopDep;

	public CFBamPopTopDepEditObj( ICFBamPopTopDepObj argOrig ) {
		super( argOrig );
		requiredContainerContRelation = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getPopTopDepTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "PopTopDep" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamRelationObj scope = getRequiredContainerContRelation();
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
		ICFBamPopTopDepObj retobj = getSchema().getPopTopDepTableObj().realisePopTopDep( (ICFBamPopTopDepObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsPopTopDep().forget();
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamPopTopDepObj retobj = ((ICFBamSchemaObj)getOrigAsPopTopDep().getSchema()).getPopTopDepTableObj().createPopTopDep( getOrigAsPopTopDep() );
		if( retobj == getOrigAsPopTopDep() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getPopTopDepTableObj().updatePopTopDep( (ICFBamPopTopDepObj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getPopTopDepTableObj().deletePopTopDep( getOrigAsPopTopDep() );
		return( null );
	}

	@Override
	public ICFBamPopTopDepTableObj getPopTopDepTable() {
		return( orig.getSchema().getPopTopDepTableObj() );
	}

	@Override
	public ICFBamPopTopDepEditObj getEditAsPopTopDep() {
		return( (ICFBamPopTopDepEditObj)this );
	}

	@Override
	public ICFBamPopTopDepObj getOrigAsPopTopDep() {
		return( (ICFBamPopTopDepObj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsPopTopDep().getSchema().getCFBamBackingStore().getFactoryPopTopDep().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerContRelation = null;
		}
	}

	@Override
	public ICFBamPopTopDep getPopTopDepRec() {
		return( (ICFBamPopTopDep)getRec() );
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
	public CFLibDbKeyHash256 getRequiredContRelationId() {
		return( getPopTopDepRec().getRequiredContRelationId() );
	}

	@Override
	public String getRequiredName() {
		return( getPopTopDepRec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getPopTopDepRec().getRequiredName() != value ) {
			getPopTopDepRec().setRequiredName( value );
		}
	}

	@Override
	public ICFBamRelationObj getRequiredContainerContRelation() {
		return( getRequiredContainerContRelation( false ) );
	}

	@Override
	public ICFBamRelationObj getRequiredContainerContRelation( boolean forceRead ) {
		if( forceRead || ( requiredContainerContRelation == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamRelationObj obj = ((ICFBamSchemaObj)getOrigAsPopTopDep().getSchema()).getRelationTableObj().readRelationByIdIdx( getPopTopDepRec().getRequiredContRelationId() );
				requiredContainerContRelation = obj;
				if( obj != null ) {
					requiredContainerContRelation = obj;
				}
			}
		}
		return( requiredContainerContRelation );
	}

	@Override
	public void setRequiredContainerContRelation( ICFBamRelationObj value ) {
		if( rec == null ) {
			getPopTopDepRec();
		}
		if( value != null ) {
			requiredContainerContRelation = value;
			getPopTopDepRec().setRequiredContainerContRelation(value.getRelationRec());
		}
		requiredContainerContRelation = value;
	}

	@Override
	public List<ICFBamPopSubDep1Obj> getOptionalComponentsPopDep() {
		List<ICFBamPopSubDep1Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getPopSubDep1TableObj().readPopSubDep1ByPopTopDepIdx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamPopSubDep1Obj> getOptionalComponentsPopDep( boolean forceRead ) {
		List<ICFBamPopSubDep1Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getPopSubDep1TableObj().readPopSubDep1ByPopTopDepIdx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public void copyRecToOrig() {
		ICFBamPopTopDep origRec = getOrigAsPopTopDep().getPopTopDepRec();
		ICFBamPopTopDep myRec = getPopTopDepRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamPopTopDep origRec = getOrigAsPopTopDep().getPopTopDepRec();
		ICFBamPopTopDep myRec = getPopTopDepRec();
		myRec.set( origRec );
	}
}
