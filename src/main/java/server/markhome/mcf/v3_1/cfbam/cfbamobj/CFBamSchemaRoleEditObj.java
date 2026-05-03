// Description: Java 25 edit object instance implementation for CFBam SchemaRole.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamSchemaRoleEditObj
	extends CFBamRoleDefEditObj

	implements ICFBamSchemaRoleEditObj
{
	protected ICFBamSchemaDefObj requiredContainerSchemaDef;

	public CFBamSchemaRoleEditObj( ICFBamSchemaRoleObj argOrig ) {
		super( argOrig );
		requiredContainerSchemaDef = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getSchemaRoleTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "SchemaRole" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamSchemaDefObj scope = getRequiredContainerSchemaDef();
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
	public ICFBamRoleDefObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamSchemaRoleObj retobj = getSchema().getSchemaRoleTableObj().realiseSchemaRole( (ICFBamSchemaRoleObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsSchemaRole().forget();
	}

	@Override
	public ICFBamRoleDefObj create() {
		copyRecToOrig();
		ICFBamSchemaRoleObj retobj = ((ICFBamSchemaObj)getOrigAsSchemaRole().getSchema()).getSchemaRoleTableObj().createSchemaRole( getOrigAsSchemaRole() );
		if( retobj == getOrigAsSchemaRole() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamRoleDefEditObj update() {
		getSchema().getSchemaRoleTableObj().updateSchemaRole( (ICFBamSchemaRoleObj)this );
		return( null );
	}

	@Override
	public CFBamRoleDefEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getSchemaRoleTableObj().deleteSchemaRole( getOrigAsSchemaRole() );
		return( null );
	}

	@Override
	public ICFBamSchemaRoleTableObj getSchemaRoleTable() {
		return( orig.getSchema().getSchemaRoleTableObj() );
	}

	@Override
	public ICFBamSchemaRoleEditObj getEditAsSchemaRole() {
		return( (ICFBamSchemaRoleEditObj)this );
	}

	@Override
	public ICFBamSchemaRoleObj getOrigAsSchemaRole() {
		return( (ICFBamSchemaRoleObj)orig );
	}

	@Override
	public ICFBamRoleDef getRec() {
		if( rec == null ) {
			rec = getOrigAsSchemaRole().getSchema().getCFBamBackingStore().getFactorySchemaRole().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamRoleDef value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerSchemaDef = null;
		}
	}

	@Override
	public ICFBamSchemaRole getSchemaRoleRec() {
		return( (ICFBamSchemaRole)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 value) {
		if (getPKey() != value) {
			setPKey(value);
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredSchemaDefId() {
		return( getSchemaRoleRec().getRequiredSchemaDefId() );
	}

	@Override
	public void setRequiredContainerScopeDef( ICFBamScopeObj value ) {
		final String S_ProcName = "CFBamSchemaRoleEditObj.setRequiredContainerScopeDef() ";
		if( value == null ) {
			setRequiredContainerSchemaDef( null );
		}
		else if( ! ( value instanceof ICFBamSchemaDefObj ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRequiredContainerScopeDef",
				"value",
				value,
				"ICFBamSchemaDefObj" );
		}
		else {
			setRequiredContainerSchemaDef( (ICFBamSchemaDefObj)value );
		}
	}

	@Override
	public ICFBamSchemaDefObj getRequiredContainerSchemaDef() {
		return( getRequiredContainerSchemaDef( false ) );
	}

	@Override
	public ICFBamSchemaDefObj getRequiredContainerSchemaDef( boolean forceRead ) {
		if( forceRead || ( requiredContainerSchemaDef == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamSchemaDefObj obj = ((ICFBamSchemaObj)getOrigAsSchemaRole().getSchema()).getSchemaDefTableObj().readSchemaDefByIdIdx( getSchemaRoleRec().getRequiredSchemaDefId() );
				requiredContainerSchemaDef = obj;
				if( obj != null ) {
					requiredContainerSchemaDef = obj;
				}
			}
		}
		return( requiredContainerSchemaDef );
	}

	@Override
	public void setRequiredContainerSchemaDef( ICFBamSchemaDefObj value ) {
		if( rec == null ) {
			getSchemaRoleRec();
		}
		if( value != null ) {
			requiredContainerSchemaDef = value;
			getSchemaRoleRec().setRequiredContainerSchemaDef(value.getSchemaDefRec());
		}
		requiredContainerSchemaDef = value;
			super.setRequiredContainerScopeDef( value );
	}

	@Override
	public void copyRecToOrig() {
		ICFBamSchemaRole origRec = getOrigAsSchemaRole().getSchemaRoleRec();
		ICFBamSchemaRole myRec = getSchemaRoleRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamSchemaRole origRec = getOrigAsSchemaRole().getSchemaRoleRec();
		ICFBamSchemaRole myRec = getSchemaRoleRec();
		myRec.set( origRec );
	}
}
