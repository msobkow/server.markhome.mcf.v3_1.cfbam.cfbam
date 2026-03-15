// Description: Java 25 Table Object implementation for SchemaDef.

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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamSchemaDefTableObj
	implements ICFBamSchemaDefTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamSchemaDef.CLASS_CODE;
	protected static final int backingClassCode = ICFBamSchemaDef.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> members;
	private Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> allSchemaDef;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > > indexByTenantIdx;
	private Map< ICFBamSchemaDefByCTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > > indexByCTenantIdx;
	private Map< ICFBamSchemaDefByMinorVersionIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > > indexByMinorVersionIdx;
	private Map< ICFBamSchemaDefByUNameIdxKey,
		ICFBamSchemaDefObj > indexByUNameIdx;
	private Map< ICFBamSchemaDefByAuthEMailIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > > indexByAuthEMailIdx;
	private Map< ICFBamSchemaDefByProjectURLIdxKey,
		Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > > indexByProjectURLIdx;
	private Map< ICFBamSchemaDefByPubURIIdxKey,
		ICFBamSchemaDefObj > indexByPubURIIdx;
	public static String TABLE_NAME = "SchemaDef";
	public static String TABLE_DBNAME = "schemadef";

	public CFBamSchemaDefTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamSchemaDefObj>();
		allSchemaDef = null;
		indexByTenantIdx = null;
		indexByCTenantIdx = null;
		indexByMinorVersionIdx = null;
		indexByUNameIdx = null;
		indexByAuthEMailIdx = null;
		indexByProjectURLIdx = null;
		indexByPubURIIdx = null;
	}

	public CFBamSchemaDefTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamSchemaDefObj>();
		allSchemaDef = null;
		indexByTenantIdx = null;
		indexByCTenantIdx = null;
		indexByMinorVersionIdx = null;
		indexByUNameIdx = null;
		indexByAuthEMailIdx = null;
		indexByProjectURLIdx = null;
		indexByPubURIIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamSchemaDefTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamSchemaDefTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSubProjectObj.class );
	}


	@Override
	public void minimizeMemory() {
		allSchemaDef = null;
		indexByTenantIdx = null;
		indexByCTenantIdx = null;
		indexByMinorVersionIdx = null;
		indexByUNameIdx = null;
		indexByAuthEMailIdx = null;
		indexByProjectURLIdx = null;
		indexByPubURIIdx = null;
		List<ICFBamSchemaDefObj> toForget = new LinkedList<ICFBamSchemaDefObj>();
		ICFBamSchemaDefObj cur = null;
		Iterator<ICFBamSchemaDefObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSchemaDefObj.
	 */
	@Override
	public ICFBamSchemaDefObj newInstance() {
		ICFBamSchemaDefObj inst = new CFBamSchemaDefObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSchemaDefObj.
	 */
	@Override
	public ICFBamSchemaDefEditObj newEditInstance( ICFBamSchemaDefObj orig ) {
		ICFBamSchemaDefEditObj edit = new CFBamSchemaDefEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamSchemaDefObj realiseSchemaDef( ICFBamSchemaDefObj Obj ) {
		ICFBamSchemaDefObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamSchemaDefObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamSchemaDefObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByCTenantIdx != null ) {
				ICFBamSchemaDefByCTenantIdxKey keyCTenantIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByCTenantIdxKey();
				keyCTenantIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapCTenantIdx = indexByCTenantIdx.get( keyCTenantIdx );
				if( mapCTenantIdx != null ) {
					mapCTenantIdx.remove( keepObj.getPKey() );
					if( mapCTenantIdx.size() <= 0 ) {
						indexByCTenantIdx.remove( keyCTenantIdx );
					}
				}
			}

			if( indexByMinorVersionIdx != null ) {
				ICFBamSchemaDefByMinorVersionIdxKey keyMinorVersionIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByMinorVersionIdxKey();
				keyMinorVersionIdx.setRequiredMinorVersionId( keepObj.getRequiredMinorVersionId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapMinorVersionIdx = indexByMinorVersionIdx.get( keyMinorVersionIdx );
				if( mapMinorVersionIdx != null ) {
					mapMinorVersionIdx.remove( keepObj.getPKey() );
					if( mapMinorVersionIdx.size() <= 0 ) {
						indexByMinorVersionIdx.remove( keyMinorVersionIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamSchemaDefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByUNameIdxKey();
				keyUNameIdx.setRequiredMinorVersionId( keepObj.getRequiredMinorVersionId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByAuthEMailIdx != null ) {
				ICFBamSchemaDefByAuthEMailIdxKey keyAuthEMailIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByAuthEMailIdxKey();
				keyAuthEMailIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				keyAuthEMailIdx.setRequiredAuthorEMail( keepObj.getRequiredAuthorEMail() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapAuthEMailIdx = indexByAuthEMailIdx.get( keyAuthEMailIdx );
				if( mapAuthEMailIdx != null ) {
					mapAuthEMailIdx.remove( keepObj.getPKey() );
					if( mapAuthEMailIdx.size() <= 0 ) {
						indexByAuthEMailIdx.remove( keyAuthEMailIdx );
					}
				}
			}

			if( indexByProjectURLIdx != null ) {
				ICFBamSchemaDefByProjectURLIdxKey keyProjectURLIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByProjectURLIdxKey();
				keyProjectURLIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				keyProjectURLIdx.setRequiredProjectURL( keepObj.getRequiredProjectURL() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapProjectURLIdx = indexByProjectURLIdx.get( keyProjectURLIdx );
				if( mapProjectURLIdx != null ) {
					mapProjectURLIdx.remove( keepObj.getPKey() );
					if( mapProjectURLIdx.size() <= 0 ) {
						indexByProjectURLIdx.remove( keyProjectURLIdx );
					}
				}
			}

			if( indexByPubURIIdx != null ) {
				ICFBamSchemaDefByPubURIIdxKey keyPubURIIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByPubURIIdxKey();
				keyPubURIIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				keyPubURIIdx.setRequiredPublishURI( keepObj.getRequiredPublishURI() );
				indexByPubURIIdx.remove( keyPubURIIdx );
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamSchemaDefObj)schema.getScopeTableObj().realiseScope( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByCTenantIdx != null ) {
				ICFBamSchemaDefByCTenantIdxKey keyCTenantIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByCTenantIdxKey();
				keyCTenantIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapCTenantIdx = indexByCTenantIdx.get( keyCTenantIdx );
				if( mapCTenantIdx != null ) {
					mapCTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByMinorVersionIdx != null ) {
				ICFBamSchemaDefByMinorVersionIdxKey keyMinorVersionIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByMinorVersionIdxKey();
				keyMinorVersionIdx.setRequiredMinorVersionId( keepObj.getRequiredMinorVersionId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapMinorVersionIdx = indexByMinorVersionIdx.get( keyMinorVersionIdx );
				if( mapMinorVersionIdx != null ) {
					mapMinorVersionIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamSchemaDefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByUNameIdxKey();
				keyUNameIdx.setRequiredMinorVersionId( keepObj.getRequiredMinorVersionId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByAuthEMailIdx != null ) {
				ICFBamSchemaDefByAuthEMailIdxKey keyAuthEMailIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByAuthEMailIdxKey();
				keyAuthEMailIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				keyAuthEMailIdx.setRequiredAuthorEMail( keepObj.getRequiredAuthorEMail() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapAuthEMailIdx = indexByAuthEMailIdx.get( keyAuthEMailIdx );
				if( mapAuthEMailIdx != null ) {
					mapAuthEMailIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByProjectURLIdx != null ) {
				ICFBamSchemaDefByProjectURLIdxKey keyProjectURLIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByProjectURLIdxKey();
				keyProjectURLIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				keyProjectURLIdx.setRequiredProjectURL( keepObj.getRequiredProjectURL() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapProjectURLIdx = indexByProjectURLIdx.get( keyProjectURLIdx );
				if( mapProjectURLIdx != null ) {
					mapProjectURLIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPubURIIdx != null ) {
				ICFBamSchemaDefByPubURIIdxKey keyPubURIIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByPubURIIdxKey();
				keyPubURIIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				keyPubURIIdx.setRequiredPublishURI( keepObj.getRequiredPublishURI() );
				indexByPubURIIdx.put( keyPubURIIdx, keepObj );
			}

			if( allSchemaDef != null ) {
				allSchemaDef.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamSchemaDefObj)schema.getScopeTableObj().realiseScope( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSchemaDef != null ) {
				allSchemaDef.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByCTenantIdx != null ) {
				ICFBamSchemaDefByCTenantIdxKey keyCTenantIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByCTenantIdxKey();
				keyCTenantIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapCTenantIdx = indexByCTenantIdx.get( keyCTenantIdx );
				if( mapCTenantIdx != null ) {
					mapCTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByMinorVersionIdx != null ) {
				ICFBamSchemaDefByMinorVersionIdxKey keyMinorVersionIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByMinorVersionIdxKey();
				keyMinorVersionIdx.setRequiredMinorVersionId( keepObj.getRequiredMinorVersionId() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapMinorVersionIdx = indexByMinorVersionIdx.get( keyMinorVersionIdx );
				if( mapMinorVersionIdx != null ) {
					mapMinorVersionIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamSchemaDefByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByUNameIdxKey();
				keyUNameIdx.setRequiredMinorVersionId( keepObj.getRequiredMinorVersionId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByAuthEMailIdx != null ) {
				ICFBamSchemaDefByAuthEMailIdxKey keyAuthEMailIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByAuthEMailIdxKey();
				keyAuthEMailIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				keyAuthEMailIdx.setRequiredAuthorEMail( keepObj.getRequiredAuthorEMail() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapAuthEMailIdx = indexByAuthEMailIdx.get( keyAuthEMailIdx );
				if( mapAuthEMailIdx != null ) {
					mapAuthEMailIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByProjectURLIdx != null ) {
				ICFBamSchemaDefByProjectURLIdxKey keyProjectURLIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByProjectURLIdxKey();
				keyProjectURLIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				keyProjectURLIdx.setRequiredProjectURL( keepObj.getRequiredProjectURL() );
				Map<CFLibDbKeyHash256, ICFBamSchemaDefObj > mapProjectURLIdx = indexByProjectURLIdx.get( keyProjectURLIdx );
				if( mapProjectURLIdx != null ) {
					mapProjectURLIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByPubURIIdx != null ) {
				ICFBamSchemaDefByPubURIIdxKey keyPubURIIdx =
					schema.getCFBamBackingStore().getFactorySchemaDef().newByPubURIIdxKey();
				keyPubURIIdx.setRequiredCTenantId( keepObj.getRequiredCTenantId() );
				keyPubURIIdx.setRequiredPublishURI( keepObj.getRequiredPublishURI() );
				indexByPubURIIdx.put( keyPubURIIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamSchemaDefObj createSchemaDef( ICFBamSchemaDefObj Obj ) {
		ICFBamSchemaDefObj obj = Obj;
		ICFBamSchemaDef rec = obj.getSchemaDefRec();
		schema.getCFBamBackingStore().getTableSchemaDef().createSchemaDef(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamSchemaDefObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamSchemaDefObj readSchemaDef( CFLibDbKeyHash256 pkey ) {
		return( readSchemaDef( pkey, false ) );
	}

	@Override
	public ICFBamSchemaDefObj readSchemaDef( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamSchemaDefObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamSchemaDef readRec = schema.getCFBamBackingStore().getTableSchemaDef().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamSchemaDefObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamSchemaDefObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamSchemaDefObj readCachedSchemaDef( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaDefObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSchemaDef( ICFBamSchemaDefObj obj )
	{
		final String S_ProcName = "CFBamSchemaDefTableObj.reallyDeepDisposeSchemaDef() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamSchemaDefObj existing = readCachedSchemaDef( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamSchemaDefByCTenantIdxKey keyCTenantIdx = schema.getCFBamBackingStore().getFactorySchemaDef().newByCTenantIdxKey();
		keyCTenantIdx.setRequiredCTenantId( existing.getRequiredCTenantId() );

		ICFBamSchemaDefByMinorVersionIdxKey keyMinorVersionIdx = schema.getCFBamBackingStore().getFactorySchemaDef().newByMinorVersionIdxKey();
		keyMinorVersionIdx.setRequiredMinorVersionId( existing.getRequiredMinorVersionId() );

		ICFBamSchemaDefByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactorySchemaDef().newByUNameIdxKey();
		keyUNameIdx.setRequiredMinorVersionId( existing.getRequiredMinorVersionId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamSchemaDefByAuthEMailIdxKey keyAuthEMailIdx = schema.getCFBamBackingStore().getFactorySchemaDef().newByAuthEMailIdxKey();
		keyAuthEMailIdx.setRequiredCTenantId( existing.getRequiredCTenantId() );
		keyAuthEMailIdx.setRequiredAuthorEMail( existing.getRequiredAuthorEMail() );

		ICFBamSchemaDefByProjectURLIdxKey keyProjectURLIdx = schema.getCFBamBackingStore().getFactorySchemaDef().newByProjectURLIdxKey();
		keyProjectURLIdx.setRequiredCTenantId( existing.getRequiredCTenantId() );
		keyProjectURLIdx.setRequiredProjectURL( existing.getRequiredProjectURL() );

		ICFBamSchemaDefByPubURIIdxKey keyPubURIIdx = schema.getCFBamBackingStore().getFactorySchemaDef().newByPubURIIdxKey();
		keyPubURIIdx.setRequiredCTenantId( existing.getRequiredCTenantId() );
		keyPubURIIdx.setRequiredPublishURI( existing.getRequiredPublishURI() );


		ICFBamTableObj objDelTableMethods;
		List<ICFBamTableObj> arrDelTableMethods = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableMethods = arrDelTableMethods.iterator();
		while( iterDelTableMethods.hasNext() ) {
			objDelTableMethods = iterDelTableMethods.next();
			if( objDelTableMethods != null ) {
						schema.getServerMethodTableObj().deepDisposeServerMethodByMethTableIdx( objDelTableMethods.getRequiredId() );
			}
		}
		ICFBamTableObj objDelTableDelDep;
		List<ICFBamTableObj> arrDelTableDelDep = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableDelDep = arrDelTableDelDep.iterator();
		while( iterDelTableDelDep.hasNext() ) {
			objDelTableDelDep = iterDelTableDelDep.next();
			if( objDelTableDelDep != null ) {
						schema.getDelTopDepTableObj().deepDisposeDelTopDepByDelTopDepTblIdx( objDelTableDelDep.getRequiredId() );
			}
		}
		ICFBamTableObj objDelTableClearDep;
		List<ICFBamTableObj> arrDelTableClearDep = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableClearDep = arrDelTableClearDep.iterator();
		while( iterDelTableClearDep.hasNext() ) {
			objDelTableClearDep = iterDelTableClearDep.next();
			if( objDelTableClearDep != null ) {
						schema.getClearTopDepTableObj().deepDisposeClearTopDepByClrTopDepTblIdx( objDelTableClearDep.getRequiredId() );
			}
		}
		ICFBamTableObj objDelTableChain;
		List<ICFBamTableObj> arrDelTableChain = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableChain = arrDelTableChain.iterator();
		while( iterDelTableChain.hasNext() ) {
			objDelTableChain = iterDelTableChain.next();
			if( objDelTableChain != null ) {
						schema.getChainTableObj().deepDisposeChainByChainTableIdx( objDelTableChain.getRequiredId() );
			}
		}
		ICFBamTableObj objDelTableRelationPopDep;
		List<ICFBamTableObj> arrDelTableRelationPopDep = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableRelationPopDep = arrDelTableRelationPopDep.iterator();
		while( iterDelTableRelationPopDep.hasNext() ) {
			objDelTableRelationPopDep = iterDelTableRelationPopDep.next();
			if( objDelTableRelationPopDep != null ) {
			ICFBamRelationObj objTableRelation;
			List<ICFBamRelationObj> arrTableRelation = schema.getRelationTableObj().readCachedRelationByRelTableIdx( objDelTableRelationPopDep.getRequiredId() );
			Iterator<ICFBamRelationObj> iterTableRelation = arrTableRelation.iterator();
			while( iterTableRelation.hasNext() ) {
				objTableRelation = iterTableRelation.next();
					schema.getPopTopDepTableObj().deepDisposePopTopDepByContRelIdx( objTableRelation.getRequiredId() );
			}
			}
		}
		ICFBamTableObj objDelTableRelationCol;
		List<ICFBamTableObj> arrDelTableRelationCol = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableRelationCol = arrDelTableRelationCol.iterator();
		while( iterDelTableRelationCol.hasNext() ) {
			objDelTableRelationCol = iterDelTableRelationCol.next();
			if( objDelTableRelationCol != null ) {
			ICFBamRelationObj objTableRelation;
			List<ICFBamRelationObj> arrTableRelation = schema.getRelationTableObj().readCachedRelationByRelTableIdx( objDelTableRelationCol.getRequiredId() );
			Iterator<ICFBamRelationObj> iterTableRelation = arrTableRelation.iterator();
			while( iterTableRelation.hasNext() ) {
				objTableRelation = iterTableRelation.next();
					schema.getRelationColTableObj().deepDisposeRelationColByRelationIdx( objTableRelation.getRequiredId() );
			}
			}
		}
		ICFBamTableObj objDelTableRelation;
		List<ICFBamTableObj> arrDelTableRelation = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableRelation = arrDelTableRelation.iterator();
		while( iterDelTableRelation.hasNext() ) {
			objDelTableRelation = iterDelTableRelation.next();
			if( objDelTableRelation != null ) {
						schema.getRelationTableObj().deepDisposeRelationByRelTableIdx( objDelTableRelation.getRequiredId() );
			}
		}
		ICFBamTableObj objDelTableIndexRefRelFmCol;
		List<ICFBamTableObj> arrDelTableIndexRefRelFmCol = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableIndexRefRelFmCol = arrDelTableIndexRefRelFmCol.iterator();
		while( iterDelTableIndexRefRelFmCol.hasNext() ) {
			objDelTableIndexRefRelFmCol = iterDelTableIndexRefRelFmCol.next();
			if( objDelTableIndexRefRelFmCol != null ) {
			ICFBamIndexObj objTableIndex;
			List<ICFBamIndexObj> arrTableIndex = schema.getIndexTableObj().readCachedIndexByIdxTableIdx( objDelTableIndexRefRelFmCol.getRequiredId() );
			Iterator<ICFBamIndexObj> iterTableIndex = arrTableIndex.iterator();
			while( iterTableIndex.hasNext() ) {
				objTableIndex = iterTableIndex.next();
			ICFBamIndexColObj objColumns;
			List<ICFBamIndexColObj> arrColumns = schema.getIndexColTableObj().readCachedIndexColByIndexIdx( objTableIndex.getRequiredId() );
			Iterator<ICFBamIndexColObj> iterColumns = arrColumns.iterator();
			while( iterColumns.hasNext() ) {
				objColumns = iterColumns.next();
					schema.getRelationColTableObj().deepDisposeRelationColByFromColIdx( objColumns.getRequiredId() );
			}
			}
			}
		}
		ICFBamTableObj objDelTableIndexRefRelToCol;
		List<ICFBamTableObj> arrDelTableIndexRefRelToCol = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableIndexRefRelToCol = arrDelTableIndexRefRelToCol.iterator();
		while( iterDelTableIndexRefRelToCol.hasNext() ) {
			objDelTableIndexRefRelToCol = iterDelTableIndexRefRelToCol.next();
			if( objDelTableIndexRefRelToCol != null ) {
			ICFBamIndexObj objTableIndex;
			List<ICFBamIndexObj> arrTableIndex = schema.getIndexTableObj().readCachedIndexByIdxTableIdx( objDelTableIndexRefRelToCol.getRequiredId() );
			Iterator<ICFBamIndexObj> iterTableIndex = arrTableIndex.iterator();
			while( iterTableIndex.hasNext() ) {
				objTableIndex = iterTableIndex.next();
			ICFBamIndexColObj objColumns;
			List<ICFBamIndexColObj> arrColumns = schema.getIndexColTableObj().readCachedIndexColByIndexIdx( objTableIndex.getRequiredId() );
			Iterator<ICFBamIndexColObj> iterColumns = arrColumns.iterator();
			while( iterColumns.hasNext() ) {
				objColumns = iterColumns.next();
					schema.getRelationColTableObj().deepDisposeRelationColByToColIdx( objColumns.getRequiredId() );
			}
			}
			}
		}
		ICFBamTableObj objDelTableIndexCols;
		List<ICFBamTableObj> arrDelTableIndexCols = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableIndexCols = arrDelTableIndexCols.iterator();
		while( iterDelTableIndexCols.hasNext() ) {
			objDelTableIndexCols = iterDelTableIndexCols.next();
			if( objDelTableIndexCols != null ) {
			ICFBamIndexObj objTableIndex;
			List<ICFBamIndexObj> arrTableIndex = schema.getIndexTableObj().readCachedIndexByIdxTableIdx( objDelTableIndexCols.getRequiredId() );
			Iterator<ICFBamIndexObj> iterTableIndex = arrTableIndex.iterator();
			while( iterTableIndex.hasNext() ) {
				objTableIndex = iterTableIndex.next();
					schema.getIndexColTableObj().deepDisposeIndexColByIndexIdx( objTableIndex.getRequiredId() );
			}
			}
		}
		ICFBamTableObj objDelTableIndexes;
		List<ICFBamTableObj> arrDelTableIndexes = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableIndexes = arrDelTableIndexes.iterator();
		while( iterDelTableIndexes.hasNext() ) {
			objDelTableIndexes = iterDelTableIndexes.next();
			if( objDelTableIndexes != null ) {
						schema.getIndexTableObj().deepDisposeIndexByIdxTableIdx( objDelTableIndexes.getRequiredId() );
			}
		}
		ICFBamTableObj objDelTableRefIndexColumns;
		List<ICFBamTableObj> arrDelTableRefIndexColumns = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableRefIndexColumns = arrDelTableRefIndexColumns.iterator();
		while( iterDelTableRefIndexColumns.hasNext() ) {
			objDelTableRefIndexColumns = iterDelTableRefIndexColumns.next();
			if( objDelTableRefIndexColumns != null ) {
			ICFBamValueObj objColumns;
			List<ICFBamValueObj> arrColumns = schema.getValueTableObj().readCachedValueByScopeIdx( objDelTableRefIndexColumns.getRequiredId() );
			Iterator<ICFBamValueObj> iterColumns = arrColumns.iterator();
			while( iterColumns.hasNext() ) {
				objColumns = iterColumns.next();
					schema.getIndexColTableObj().deepDisposeIndexColByColIdx( objColumns.getRequiredId() );
			}
			}
		}
		ICFBamTableObj objDelTableColumns;
		List<ICFBamTableObj> arrDelTableColumns = schema.getTableTableObj().readCachedTableBySchemaDefIdx( existing.getRequiredId() );
		Iterator<ICFBamTableObj> iterDelTableColumns = arrDelTableColumns.iterator();
		while( iterDelTableColumns.hasNext() ) {
			objDelTableColumns = iterDelTableColumns.next();
			if( objDelTableColumns != null ) {
						schema.getValueTableObj().deepDisposeValueByScopeIdx( objDelTableColumns.getRequiredId() );
			}
		}
					schema.getTableTableObj().deepDisposeTableBySchemaDefIdx( existing.getRequiredId() );
		ICFBamValueObj objDelTypeRefs;
		List<ICFBamValueObj> arrDelTypeRefs = schema.getValueTableObj().readCachedValueByScopeIdx( existing.getRequiredId() );
		Iterator<ICFBamValueObj> iterDelTypeRefs = arrDelTypeRefs.iterator();
		while( iterDelTypeRefs.hasNext() ) {
			objDelTypeRefs = iterDelTypeRefs.next();
			if( objDelTypeRefs != null ) {
						schema.getTableColTableObj().deepDisposeTableColByDataIdx( objDelTypeRefs.getRequiredId() );
			}
		}
					schema.getValueTableObj().deepDisposeValueByScopeIdx( existing.getRequiredId() );

		if( indexByCTenantIdx != null ) {
			if( indexByCTenantIdx.containsKey( keyCTenantIdx ) ) {
				indexByCTenantIdx.get( keyCTenantIdx ).remove( pkey );
				if( indexByCTenantIdx.get( keyCTenantIdx ).size() <= 0 ) {
					indexByCTenantIdx.remove( keyCTenantIdx );
				}
			}
		}

		if( indexByMinorVersionIdx != null ) {
			if( indexByMinorVersionIdx.containsKey( keyMinorVersionIdx ) ) {
				indexByMinorVersionIdx.get( keyMinorVersionIdx ).remove( pkey );
				if( indexByMinorVersionIdx.get( keyMinorVersionIdx ).size() <= 0 ) {
					indexByMinorVersionIdx.remove( keyMinorVersionIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByAuthEMailIdx != null ) {
			if( indexByAuthEMailIdx.containsKey( keyAuthEMailIdx ) ) {
				indexByAuthEMailIdx.get( keyAuthEMailIdx ).remove( pkey );
				if( indexByAuthEMailIdx.get( keyAuthEMailIdx ).size() <= 0 ) {
					indexByAuthEMailIdx.remove( keyAuthEMailIdx );
				}
			}
		}

		if( indexByProjectURLIdx != null ) {
			if( indexByProjectURLIdx.containsKey( keyProjectURLIdx ) ) {
				indexByProjectURLIdx.get( keyProjectURLIdx ).remove( pkey );
				if( indexByProjectURLIdx.get( keyProjectURLIdx ).size() <= 0 ) {
					indexByProjectURLIdx.remove( keyProjectURLIdx );
				}
			}
		}

		if( indexByPubURIIdx != null ) {
			indexByPubURIIdx.remove( keyPubURIIdx );
		}


		schema.getScopeTableObj().reallyDeepDisposeScope( obj );
	}
	@Override
	public void deepDisposeSchemaDef( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaDefObj obj = readCachedSchemaDef( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamSchemaDefObj lockSchemaDef( CFLibDbKeyHash256 pkey ) {
		ICFBamSchemaDefObj locked = null;
		ICFBamSchemaDef lockRec = schema.getCFBamBackingStore().getTableSchemaDef().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamSchemaDefObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamSchemaDefObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSchemaDef", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamSchemaDefObj> readAllSchemaDef() {
		return( readAllSchemaDef( false ) );
	}

	@Override
	public List<ICFBamSchemaDefObj> readAllSchemaDef( boolean forceRead ) {
		final String S_ProcName = "readAllSchemaDef";
		if( ( allSchemaDef == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> map = new HashMap<CFLibDbKeyHash256,ICFBamSchemaDefObj>();
			allSchemaDef = map;
			ICFBamSchemaDef[] recList = schema.getCFBamBackingStore().getTableSchemaDef().readAllDerived( null );
			ICFBamSchemaDef rec;
			ICFBamSchemaDefObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaDefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaDefObj realised = (ICFBamSchemaDefObj)obj.realise();
			}
		}
		int len = allSchemaDef.size();
		ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
		Iterator<ICFBamSchemaDefObj> valIter = allSchemaDef.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaDefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaDefObj> readCachedAllSchemaDef() {
		final String S_ProcName = "readCachedAllSchemaDef";
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>();
		if( allSchemaDef != null ) {
			int len = allSchemaDef.size();
			ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
			Iterator<ICFBamSchemaDefObj> valIter = allSchemaDef.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamSchemaDefObj readSchemaDefByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readSchemaDefByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamSchemaDefObj readSchemaDefByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamSchemaDefObj obj = readSchemaDef( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamSchemaDefObj> readSchemaDefByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readSchemaDefByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaDefObj> readSchemaDefByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaDefByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaDefObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaDefObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaDefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaDefObj realised = (ICFBamSchemaDefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
		Iterator<ICFBamSchemaDefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaDefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaDefObj> readSchemaDefByCTenantIdx( CFLibDbKeyHash256 CTenantId )
	{
		return( readSchemaDefByCTenantIdx( CTenantId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaDefObj> readSchemaDefByCTenantIdx( CFLibDbKeyHash256 CTenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaDefByCTenantIdx";
		ICFBamSchemaDefByCTenantIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByCTenantIdxKey();
		key.setRequiredCTenantId( CTenantId );
		Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict;
		if( indexByCTenantIdx == null ) {
			indexByCTenantIdx = new HashMap< ICFBamSchemaDefByCTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaDefObj > >();
		}
		if( ( ! forceRead ) && indexByCTenantIdx.containsKey( key ) ) {
			dict = indexByCTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaDefObj>();
			ICFBamSchemaDefObj obj;
			ICFBamSchemaDef[] recList = schema.getCFBamBackingStore().getTableSchemaDef().readDerivedByCTenantIdx( null,
				CTenantId );
			ICFBamSchemaDef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaDefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaDefObj realised = (ICFBamSchemaDefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByCTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
		Iterator<ICFBamSchemaDefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaDefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaDefObj> readSchemaDefByMinorVersionIdx( CFLibDbKeyHash256 MinorVersionId )
	{
		return( readSchemaDefByMinorVersionIdx( MinorVersionId,
			false ) );
	}

	@Override
	public List<ICFBamSchemaDefObj> readSchemaDefByMinorVersionIdx( CFLibDbKeyHash256 MinorVersionId,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaDefByMinorVersionIdx";
		ICFBamSchemaDefByMinorVersionIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByMinorVersionIdxKey();
		key.setRequiredMinorVersionId( MinorVersionId );
		Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict;
		if( indexByMinorVersionIdx == null ) {
			indexByMinorVersionIdx = new HashMap< ICFBamSchemaDefByMinorVersionIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaDefObj > >();
		}
		if( ( ! forceRead ) && indexByMinorVersionIdx.containsKey( key ) ) {
			dict = indexByMinorVersionIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaDefObj>();
			ICFBamSchemaDefObj obj;
			ICFBamSchemaDef[] recList = schema.getCFBamBackingStore().getTableSchemaDef().readDerivedByMinorVersionIdx( null,
				MinorVersionId );
			ICFBamSchemaDef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaDefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaDefObj realised = (ICFBamSchemaDefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByMinorVersionIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
		Iterator<ICFBamSchemaDefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaDefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamSchemaDefObj readSchemaDefByUNameIdx( CFLibDbKeyHash256 MinorVersionId,
		String Name )
	{
		return( readSchemaDefByUNameIdx( MinorVersionId,
			Name,
			false ) );
	}

	@Override
	public ICFBamSchemaDefObj readSchemaDefByUNameIdx( CFLibDbKeyHash256 MinorVersionId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamSchemaDefByUNameIdxKey,
				ICFBamSchemaDefObj >();
		}
		ICFBamSchemaDefByUNameIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByUNameIdxKey();
		key.setRequiredMinorVersionId( MinorVersionId );
		key.setRequiredName( Name );
		ICFBamSchemaDefObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamSchemaDef rec = schema.getCFBamBackingStore().getTableSchemaDef().readDerivedByUNameIdx( null,
				MinorVersionId,
				Name );
			if( rec != null ) {
				obj = (ICFBamSchemaDefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamSchemaDefObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamSchemaDefObj> readSchemaDefByAuthEMailIdx( CFLibDbKeyHash256 CTenantId,
		String AuthorEMail )
	{
		return( readSchemaDefByAuthEMailIdx( CTenantId,
			AuthorEMail,
			false ) );
	}

	@Override
	public List<ICFBamSchemaDefObj> readSchemaDefByAuthEMailIdx( CFLibDbKeyHash256 CTenantId,
		String AuthorEMail,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaDefByAuthEMailIdx";
		ICFBamSchemaDefByAuthEMailIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByAuthEMailIdxKey();
		key.setRequiredCTenantId( CTenantId );
		key.setRequiredAuthorEMail( AuthorEMail );
		Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict;
		if( indexByAuthEMailIdx == null ) {
			indexByAuthEMailIdx = new HashMap< ICFBamSchemaDefByAuthEMailIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaDefObj > >();
		}
		if( ( ! forceRead ) && indexByAuthEMailIdx.containsKey( key ) ) {
			dict = indexByAuthEMailIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaDefObj>();
			ICFBamSchemaDefObj obj;
			ICFBamSchemaDef[] recList = schema.getCFBamBackingStore().getTableSchemaDef().readDerivedByAuthEMailIdx( null,
				CTenantId,
				AuthorEMail );
			ICFBamSchemaDef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaDefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaDefObj realised = (ICFBamSchemaDefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByAuthEMailIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
		Iterator<ICFBamSchemaDefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaDefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamSchemaDefObj> readSchemaDefByProjectURLIdx( CFLibDbKeyHash256 CTenantId,
		String ProjectURL )
	{
		return( readSchemaDefByProjectURLIdx( CTenantId,
			ProjectURL,
			false ) );
	}

	@Override
	public List<ICFBamSchemaDefObj> readSchemaDefByProjectURLIdx( CFLibDbKeyHash256 CTenantId,
		String ProjectURL,
		boolean forceRead )
	{
		final String S_ProcName = "readSchemaDefByProjectURLIdx";
		ICFBamSchemaDefByProjectURLIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByProjectURLIdxKey();
		key.setRequiredCTenantId( CTenantId );
		key.setRequiredProjectURL( ProjectURL );
		Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict;
		if( indexByProjectURLIdx == null ) {
			indexByProjectURLIdx = new HashMap< ICFBamSchemaDefByProjectURLIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaDefObj > >();
		}
		if( ( ! forceRead ) && indexByProjectURLIdx.containsKey( key ) ) {
			dict = indexByProjectURLIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamSchemaDefObj>();
			ICFBamSchemaDefObj obj;
			ICFBamSchemaDef[] recList = schema.getCFBamBackingStore().getTableSchemaDef().readDerivedByProjectURLIdx( null,
				CTenantId,
				ProjectURL );
			ICFBamSchemaDef rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamSchemaDefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamSchemaDefObj realised = (ICFBamSchemaDefObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByProjectURLIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
		Iterator<ICFBamSchemaDefObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamSchemaDefObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamSchemaDefObj readSchemaDefByPubURIIdx( CFLibDbKeyHash256 CTenantId,
		String PublishURI )
	{
		return( readSchemaDefByPubURIIdx( CTenantId,
			PublishURI,
			false ) );
	}

	@Override
	public ICFBamSchemaDefObj readSchemaDefByPubURIIdx( CFLibDbKeyHash256 CTenantId,
		String PublishURI, boolean forceRead )
	{
		if( indexByPubURIIdx == null ) {
			indexByPubURIIdx = new HashMap< ICFBamSchemaDefByPubURIIdxKey,
				ICFBamSchemaDefObj >();
		}
		ICFBamSchemaDefByPubURIIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByPubURIIdxKey();
		key.setRequiredCTenantId( CTenantId );
		key.setRequiredPublishURI( PublishURI );
		ICFBamSchemaDefObj obj = null;
		if( ( ! forceRead ) && indexByPubURIIdx.containsKey( key ) ) {
			obj = indexByPubURIIdx.get( key );
		}
		else {
			ICFBamSchemaDef rec = schema.getCFBamBackingStore().getTableSchemaDef().readDerivedByPubURIIdx( null,
				CTenantId,
				PublishURI );
			if( rec != null ) {
				obj = (ICFBamSchemaDefObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamSchemaDefObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamSchemaDefObj readCachedSchemaDefByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaDefObj obj = null;
		obj = readCachedSchemaDef( Id );
		return( obj );
	}

	@Override
	public List<ICFBamSchemaDefObj> readCachedSchemaDefByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedSchemaDefByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
				Iterator<ICFBamSchemaDefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaDefObj obj;
			Iterator<ICFBamSchemaDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamSchemaDefObj> readCachedSchemaDefByCTenantIdx( CFLibDbKeyHash256 CTenantId )
	{
		final String S_ProcName = "readCachedSchemaDefByCTenantIdx";
		ICFBamSchemaDefByCTenantIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByCTenantIdxKey();
		key.setRequiredCTenantId( CTenantId );
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>();
		if( indexByCTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict;
			if( indexByCTenantIdx.containsKey( key ) ) {
				dict = indexByCTenantIdx.get( key );
				int len = dict.size();
				ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
				Iterator<ICFBamSchemaDefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaDefObj obj;
			Iterator<ICFBamSchemaDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamSchemaDefObj> readCachedSchemaDefByMinorVersionIdx( CFLibDbKeyHash256 MinorVersionId )
	{
		final String S_ProcName = "readCachedSchemaDefByMinorVersionIdx";
		ICFBamSchemaDefByMinorVersionIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByMinorVersionIdxKey();
		key.setRequiredMinorVersionId( MinorVersionId );
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>();
		if( indexByMinorVersionIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict;
			if( indexByMinorVersionIdx.containsKey( key ) ) {
				dict = indexByMinorVersionIdx.get( key );
				int len = dict.size();
				ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
				Iterator<ICFBamSchemaDefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaDefObj obj;
			Iterator<ICFBamSchemaDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamSchemaDefObj readCachedSchemaDefByUNameIdx( CFLibDbKeyHash256 MinorVersionId,
		String Name )
	{
		ICFBamSchemaDefObj obj = null;
		ICFBamSchemaDefByUNameIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByUNameIdxKey();
		key.setRequiredMinorVersionId( MinorVersionId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamSchemaDefObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamSchemaDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamSchemaDefObj> readCachedSchemaDefByAuthEMailIdx( CFLibDbKeyHash256 CTenantId,
		String AuthorEMail )
	{
		final String S_ProcName = "readCachedSchemaDefByAuthEMailIdx";
		ICFBamSchemaDefByAuthEMailIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByAuthEMailIdxKey();
		key.setRequiredCTenantId( CTenantId );
		key.setRequiredAuthorEMail( AuthorEMail );
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>();
		if( indexByAuthEMailIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict;
			if( indexByAuthEMailIdx.containsKey( key ) ) {
				dict = indexByAuthEMailIdx.get( key );
				int len = dict.size();
				ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
				Iterator<ICFBamSchemaDefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaDefObj obj;
			Iterator<ICFBamSchemaDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamSchemaDefObj> readCachedSchemaDefByProjectURLIdx( CFLibDbKeyHash256 CTenantId,
		String ProjectURL )
	{
		final String S_ProcName = "readCachedSchemaDefByProjectURLIdx";
		ICFBamSchemaDefByProjectURLIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByProjectURLIdxKey();
		key.setRequiredCTenantId( CTenantId );
		key.setRequiredProjectURL( ProjectURL );
		ArrayList<ICFBamSchemaDefObj> arrayList = new ArrayList<ICFBamSchemaDefObj>();
		if( indexByProjectURLIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict;
			if( indexByProjectURLIdx.containsKey( key ) ) {
				dict = indexByProjectURLIdx.get( key );
				int len = dict.size();
				ICFBamSchemaDefObj arr[] = new ICFBamSchemaDefObj[len];
				Iterator<ICFBamSchemaDefObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamSchemaDefObj obj;
			Iterator<ICFBamSchemaDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamSchemaDefObj> cmp = new Comparator<ICFBamSchemaDefObj>() {
			@Override
			public int compare( ICFBamSchemaDefObj lhs, ICFBamSchemaDefObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamSchemaDefObj readCachedSchemaDefByPubURIIdx( CFLibDbKeyHash256 CTenantId,
		String PublishURI )
	{
		ICFBamSchemaDefObj obj = null;
		ICFBamSchemaDefByPubURIIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByPubURIIdxKey();
		key.setRequiredCTenantId( CTenantId );
		key.setRequiredPublishURI( PublishURI );
		if( indexByPubURIIdx != null ) {
			if( indexByPubURIIdx.containsKey( key ) ) {
				obj = indexByPubURIIdx.get( key );
			}
			else {
				Iterator<ICFBamSchemaDefObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamSchemaDefObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposeSchemaDefByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaDefObj obj = readCachedSchemaDefByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSchemaDefByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeSchemaDefByTenantIdx";
		ICFBamSchemaDefObj obj;
		List<ICFBamSchemaDefObj> arrayList = readCachedSchemaDefByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaDefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaDefByCTenantIdx( CFLibDbKeyHash256 CTenantId )
	{
		final String S_ProcName = "deepDisposeSchemaDefByCTenantIdx";
		ICFBamSchemaDefObj obj;
		List<ICFBamSchemaDefObj> arrayList = readCachedSchemaDefByCTenantIdx( CTenantId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaDefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaDefByMinorVersionIdx( CFLibDbKeyHash256 MinorVersionId )
	{
		final String S_ProcName = "deepDisposeSchemaDefByMinorVersionIdx";
		ICFBamSchemaDefObj obj;
		List<ICFBamSchemaDefObj> arrayList = readCachedSchemaDefByMinorVersionIdx( MinorVersionId );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaDefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaDefByUNameIdx( CFLibDbKeyHash256 MinorVersionId,
		String Name )
	{
		ICFBamSchemaDefObj obj = readCachedSchemaDefByUNameIdx( MinorVersionId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSchemaDefByAuthEMailIdx( CFLibDbKeyHash256 CTenantId,
		String AuthorEMail )
	{
		final String S_ProcName = "deepDisposeSchemaDefByAuthEMailIdx";
		ICFBamSchemaDefObj obj;
		List<ICFBamSchemaDefObj> arrayList = readCachedSchemaDefByAuthEMailIdx( CTenantId,
				AuthorEMail );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaDefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaDefByProjectURLIdx( CFLibDbKeyHash256 CTenantId,
		String ProjectURL )
	{
		final String S_ProcName = "deepDisposeSchemaDefByProjectURLIdx";
		ICFBamSchemaDefObj obj;
		List<ICFBamSchemaDefObj> arrayList = readCachedSchemaDefByProjectURLIdx( CTenantId,
				ProjectURL );
		if( arrayList != null )  {
			Iterator<ICFBamSchemaDefObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSchemaDefByPubURIIdx( CFLibDbKeyHash256 CTenantId,
		String PublishURI )
	{
		ICFBamSchemaDefObj obj = readCachedSchemaDefByPubURIIdx( CTenantId,
				PublishURI );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamSchemaDefObj updateSchemaDef( ICFBamSchemaDefObj Obj ) {
		ICFBamSchemaDefObj obj = Obj;
		schema.getCFBamBackingStore().getTableSchemaDef().updateSchemaDef( null,
			Obj.getSchemaDefRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getSchemaDefTableObj().getClassCode() ) {
			obj = (ICFBamSchemaDefObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSchemaDef( ICFBamSchemaDefObj Obj ) {
		ICFBamSchemaDefObj obj = Obj;
		schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDef( null,
			obj.getSchemaDefRec() );
		Obj.forget();
	}

	@Override
	public void deleteSchemaDefByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamSchemaDefObj obj = readSchemaDef(Id);
		if( obj != null ) {
			ICFBamSchemaDefEditObj editObj = (ICFBamSchemaDefEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamSchemaDefEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSchemaDefByIdIdx( Id );
	}

	@Override
	public void deleteSchemaDefByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaDefObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByTenantIdx( null,
				TenantId );
			Iterator<ICFBamSchemaDefObj> iter = dict.values().iterator();
			ICFBamSchemaDefObj obj;
			List<ICFBamSchemaDefObj> toForget = new LinkedList<ICFBamSchemaDefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByTenantIdx( null,
				TenantId );
		}
		deepDisposeSchemaDefByTenantIdx( TenantId );
	}

	@Override
	public void deleteSchemaDefByCTenantIdx( CFLibDbKeyHash256 CTenantId )
	{
		ICFBamSchemaDefByCTenantIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByCTenantIdxKey();
		key.setRequiredCTenantId( CTenantId );
		if( indexByCTenantIdx == null ) {
			indexByCTenantIdx = new HashMap< ICFBamSchemaDefByCTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaDefObj > >();
		}
		if( indexByCTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict = indexByCTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByCTenantIdx( null,
				CTenantId );
			Iterator<ICFBamSchemaDefObj> iter = dict.values().iterator();
			ICFBamSchemaDefObj obj;
			List<ICFBamSchemaDefObj> toForget = new LinkedList<ICFBamSchemaDefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByCTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByCTenantIdx( null,
				CTenantId );
		}
		deepDisposeSchemaDefByCTenantIdx( CTenantId );
	}

	@Override
	public void deleteSchemaDefByMinorVersionIdx( CFLibDbKeyHash256 MinorVersionId )
	{
		ICFBamSchemaDefByMinorVersionIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByMinorVersionIdxKey();
		key.setRequiredMinorVersionId( MinorVersionId );
		if( indexByMinorVersionIdx == null ) {
			indexByMinorVersionIdx = new HashMap< ICFBamSchemaDefByMinorVersionIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaDefObj > >();
		}
		if( indexByMinorVersionIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict = indexByMinorVersionIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByMinorVersionIdx( null,
				MinorVersionId );
			Iterator<ICFBamSchemaDefObj> iter = dict.values().iterator();
			ICFBamSchemaDefObj obj;
			List<ICFBamSchemaDefObj> toForget = new LinkedList<ICFBamSchemaDefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByMinorVersionIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByMinorVersionIdx( null,
				MinorVersionId );
		}
		deepDisposeSchemaDefByMinorVersionIdx( MinorVersionId );
	}

	@Override
	public void deleteSchemaDefByUNameIdx( CFLibDbKeyHash256 MinorVersionId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamSchemaDefByUNameIdxKey,
				ICFBamSchemaDefObj >();
		}
		ICFBamSchemaDefByUNameIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByUNameIdxKey();
		key.setRequiredMinorVersionId( MinorVersionId );
		key.setRequiredName( Name );
		ICFBamSchemaDefObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByUNameIdx( null,
				MinorVersionId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByUNameIdx( null,
				MinorVersionId,
				Name );
		}
		deepDisposeSchemaDefByUNameIdx( MinorVersionId,
				Name );
	}

	@Override
	public void deleteSchemaDefByAuthEMailIdx( CFLibDbKeyHash256 CTenantId,
		String AuthorEMail )
	{
		ICFBamSchemaDefByAuthEMailIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByAuthEMailIdxKey();
		key.setRequiredCTenantId( CTenantId );
		key.setRequiredAuthorEMail( AuthorEMail );
		if( indexByAuthEMailIdx == null ) {
			indexByAuthEMailIdx = new HashMap< ICFBamSchemaDefByAuthEMailIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaDefObj > >();
		}
		if( indexByAuthEMailIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict = indexByAuthEMailIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByAuthEMailIdx( null,
				CTenantId,
				AuthorEMail );
			Iterator<ICFBamSchemaDefObj> iter = dict.values().iterator();
			ICFBamSchemaDefObj obj;
			List<ICFBamSchemaDefObj> toForget = new LinkedList<ICFBamSchemaDefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByAuthEMailIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByAuthEMailIdx( null,
				CTenantId,
				AuthorEMail );
		}
		deepDisposeSchemaDefByAuthEMailIdx( CTenantId,
				AuthorEMail );
	}

	@Override
	public void deleteSchemaDefByProjectURLIdx( CFLibDbKeyHash256 CTenantId,
		String ProjectURL )
	{
		ICFBamSchemaDefByProjectURLIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByProjectURLIdxKey();
		key.setRequiredCTenantId( CTenantId );
		key.setRequiredProjectURL( ProjectURL );
		if( indexByProjectURLIdx == null ) {
			indexByProjectURLIdx = new HashMap< ICFBamSchemaDefByProjectURLIdxKey,
				Map< CFLibDbKeyHash256, ICFBamSchemaDefObj > >();
		}
		if( indexByProjectURLIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamSchemaDefObj> dict = indexByProjectURLIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByProjectURLIdx( null,
				CTenantId,
				ProjectURL );
			Iterator<ICFBamSchemaDefObj> iter = dict.values().iterator();
			ICFBamSchemaDefObj obj;
			List<ICFBamSchemaDefObj> toForget = new LinkedList<ICFBamSchemaDefObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByProjectURLIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByProjectURLIdx( null,
				CTenantId,
				ProjectURL );
		}
		deepDisposeSchemaDefByProjectURLIdx( CTenantId,
				ProjectURL );
	}

	@Override
	public void deleteSchemaDefByPubURIIdx( CFLibDbKeyHash256 CTenantId,
		String PublishURI )
	{
		if( indexByPubURIIdx == null ) {
			indexByPubURIIdx = new HashMap< ICFBamSchemaDefByPubURIIdxKey,
				ICFBamSchemaDefObj >();
		}
		ICFBamSchemaDefByPubURIIdxKey key = schema.getCFBamBackingStore().getFactorySchemaDef().newByPubURIIdxKey();
		key.setRequiredCTenantId( CTenantId );
		key.setRequiredPublishURI( PublishURI );
		ICFBamSchemaDefObj obj = null;
		if( indexByPubURIIdx.containsKey( key ) ) {
			obj = indexByPubURIIdx.get( key );
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByPubURIIdx( null,
				CTenantId,
				PublishURI );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableSchemaDef().deleteSchemaDefByPubURIIdx( null,
				CTenantId,
				PublishURI );
		}
		deepDisposeSchemaDefByPubURIIdx( CTenantId,
				PublishURI );
	}
}