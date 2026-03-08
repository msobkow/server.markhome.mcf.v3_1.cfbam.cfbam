// Description: Java 25 Table Object implementation for ClearTopDep.

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

public class CFBamClearTopDepTableObj
	implements ICFBamClearTopDepTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamClearTopDep.CLASS_CODE;
	protected static final int backingClassCode = ICFBamClearTopDep.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> members;
	private Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> allClearTopDep;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > > indexByTenantIdx;
	private Map< ICFBamClearDepByClearDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > > indexByClearDepIdx;
	private Map< ICFBamClearDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > > indexByDefSchemaIdx;
	private Map< ICFBamClearTopDepByClrTopDepTblIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > > indexByClrTopDepTblIdx;
	private Map< ICFBamClearTopDepByUNameIdxKey,
		ICFBamClearTopDepObj > indexByUNameIdx;
	private Map< ICFBamClearTopDepByPrevIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > > indexByPrevIdx;
	private Map< ICFBamClearTopDepByNextIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > > indexByNextIdx;
	public static String TABLE_NAME = "ClearTopDep";
	public static String TABLE_DBNAME = "clr_topdep";

	public CFBamClearTopDepTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamClearTopDepObj>();
		allClearTopDep = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClrTopDepTblIdx = null;
		indexByUNameIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
	}

	public CFBamClearTopDepTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamClearTopDepObj>();
		allClearTopDep = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClrTopDepTblIdx = null;
		indexByUNameIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamClearTopDepTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamClearTopDepTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allClearTopDep = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClrTopDepTblIdx = null;
		indexByUNameIdx = null;
		indexByPrevIdx = null;
		indexByNextIdx = null;
		List<ICFBamClearTopDepObj> toForget = new LinkedList<ICFBamClearTopDepObj>();
		ICFBamClearTopDepObj cur = null;
		Iterator<ICFBamClearTopDepObj> iter = members.values().iterator();
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
	 *	CFBamClearTopDepObj.
	 */
	@Override
	public ICFBamClearTopDepObj newInstance() {
		ICFBamClearTopDepObj inst = new CFBamClearTopDepObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamClearTopDepObj.
	 */
	@Override
	public ICFBamClearTopDepEditObj newEditInstance( ICFBamClearTopDepObj orig ) {
		ICFBamClearTopDepEditObj edit = new CFBamClearTopDepEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamClearTopDepObj realiseClearTopDep( ICFBamClearTopDepObj Obj ) {
		ICFBamClearTopDepObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamClearTopDepObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamClearTopDepObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					indexByClearDepIdx.remove( keyClearDepIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByClrTopDepTblIdx != null ) {
				ICFBamClearTopDepByClrTopDepTblIdxKey keyClrTopDepTblIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByClrTopDepTblIdxKey();
				keyClrTopDepTblIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapClrTopDepTblIdx = indexByClrTopDepTblIdx.get( keyClrTopDepTblIdx );
				if( mapClrTopDepTblIdx != null ) {
					mapClrTopDepTblIdx.remove( keepObj.getPKey() );
					if( mapClrTopDepTblIdx.size() <= 0 ) {
						indexByClrTopDepTblIdx.remove( keyClrTopDepTblIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearTopDepByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByPrevIdx != null ) {
				ICFBamClearTopDepByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.remove( keepObj.getPKey() );
					if( mapPrevIdx.size() <= 0 ) {
						indexByPrevIdx.remove( keyPrevIdx );
					}
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamClearTopDepByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.remove( keepObj.getPKey() );
					if( mapNextIdx.size() <= 0 ) {
						indexByNextIdx.remove( keyNextIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamClearTopDepObj)schema.getClearDepTableObj().realiseClearDep( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClrTopDepTblIdx != null ) {
				ICFBamClearTopDepByClrTopDepTblIdxKey keyClrTopDepTblIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByClrTopDepTblIdxKey();
				keyClrTopDepTblIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapClrTopDepTblIdx = indexByClrTopDepTblIdx.get( keyClrTopDepTblIdx );
				if( mapClrTopDepTblIdx != null ) {
					mapClrTopDepTblIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearTopDepByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByPrevIdx != null ) {
				ICFBamClearTopDepByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamClearTopDepByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allClearTopDep != null ) {
				allClearTopDep.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamClearTopDepObj)schema.getClearDepTableObj().realiseClearDep( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allClearTopDep != null ) {
				allClearTopDep.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClrTopDepTblIdx != null ) {
				ICFBamClearTopDepByClrTopDepTblIdxKey keyClrTopDepTblIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByClrTopDepTblIdxKey();
				keyClrTopDepTblIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapClrTopDepTblIdx = indexByClrTopDepTblIdx.get( keyClrTopDepTblIdx );
				if( mapClrTopDepTblIdx != null ) {
					mapClrTopDepTblIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearTopDepByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByPrevIdx != null ) {
				ICFBamClearTopDepByPrevIdxKey keyPrevIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByPrevIdxKey();
				keyPrevIdx.setOptionalPrevId( keepObj.getOptionalPrevId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapPrevIdx = indexByPrevIdx.get( keyPrevIdx );
				if( mapPrevIdx != null ) {
					mapPrevIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNextIdx != null ) {
				ICFBamClearTopDepByNextIdxKey keyNextIdx =
					schema.getCFBamBackingStore().getFactoryClearTopDep().newByNextIdxKey();
				keyNextIdx.setOptionalNextId( keepObj.getOptionalNextId() );
				Map<CFLibDbKeyHash256, ICFBamClearTopDepObj > mapNextIdx = indexByNextIdx.get( keyNextIdx );
				if( mapNextIdx != null ) {
					mapNextIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamClearTopDepObj createClearTopDep( ICFBamClearTopDepObj Obj ) {
		ICFBamClearTopDepObj obj = Obj;
		ICFBamClearTopDep rec = obj.getClearTopDepRec();
		schema.getCFBamBackingStore().getTableClearTopDep().createClearTopDep(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamClearTopDepObj)(obj.realise());
		}
		ICFBamClearTopDepObj prev = obj.getOptionalLookupPrev();
		if( prev != null ) {
			prev.read( true );
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamClearTopDepObj readClearTopDep( CFLibDbKeyHash256 pkey ) {
		return( readClearTopDep( pkey, false ) );
	}

	@Override
	public ICFBamClearTopDepObj readClearTopDep( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamClearTopDepObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamClearTopDep readRec = schema.getCFBamBackingStore().getTableClearTopDep().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamClearTopDepObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamClearTopDepObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamClearTopDepObj readCachedClearTopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamClearTopDepObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeClearTopDep( ICFBamClearTopDepObj obj )
	{
		final String S_ProcName = "CFBamClearTopDepTableObj.reallyDeepDisposeClearTopDep() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamClearTopDepObj existing = readCachedClearTopDep( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamClearTopDepByClrTopDepTblIdxKey keyClrTopDepTblIdx = schema.getCFBamBackingStore().getFactoryClearTopDep().newByClrTopDepTblIdxKey();
		keyClrTopDepTblIdx.setRequiredTableId( existing.getRequiredTableId() );

		ICFBamClearTopDepByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryClearTopDep().newByUNameIdxKey();
		keyUNameIdx.setRequiredTableId( existing.getRequiredTableId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );

		ICFBamClearTopDepByPrevIdxKey keyPrevIdx = schema.getCFBamBackingStore().getFactoryClearTopDep().newByPrevIdxKey();
		keyPrevIdx.setOptionalPrevId( existing.getOptionalPrevId() );

		ICFBamClearTopDepByNextIdxKey keyNextIdx = schema.getCFBamBackingStore().getFactoryClearTopDep().newByNextIdxKey();
		keyNextIdx.setOptionalNextId( existing.getOptionalNextId() );


		schema.getClearSubDep1TableObj().deepDisposeClearSubDep1ByClearTopDepIdx( existing.getRequiredId() );

		if( indexByClrTopDepTblIdx != null ) {
			if( indexByClrTopDepTblIdx.containsKey( keyClrTopDepTblIdx ) ) {
				indexByClrTopDepTblIdx.get( keyClrTopDepTblIdx ).remove( pkey );
				if( indexByClrTopDepTblIdx.get( keyClrTopDepTblIdx ).size() <= 0 ) {
					indexByClrTopDepTblIdx.remove( keyClrTopDepTblIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}

		if( indexByPrevIdx != null ) {
			if( indexByPrevIdx.containsKey( keyPrevIdx ) ) {
				indexByPrevIdx.get( keyPrevIdx ).remove( pkey );
				if( indexByPrevIdx.get( keyPrevIdx ).size() <= 0 ) {
					indexByPrevIdx.remove( keyPrevIdx );
				}
			}
		}

		if( indexByNextIdx != null ) {
			if( indexByNextIdx.containsKey( keyNextIdx ) ) {
				indexByNextIdx.get( keyNextIdx ).remove( pkey );
				if( indexByNextIdx.get( keyNextIdx ).size() <= 0 ) {
					indexByNextIdx.remove( keyNextIdx );
				}
			}
		}


		schema.getClearDepTableObj().reallyDeepDisposeClearDep( obj );
	}
	@Override
	public void deepDisposeClearTopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamClearTopDepObj obj = readCachedClearTopDep( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamClearTopDepObj lockClearTopDep( CFLibDbKeyHash256 pkey ) {
		ICFBamClearTopDepObj locked = null;
		ICFBamClearTopDep lockRec = schema.getCFBamBackingStore().getTableClearTopDep().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamClearTopDepObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamClearTopDepObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockClearTopDep", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamClearTopDepObj> readAllClearTopDep() {
		return( readAllClearTopDep( false ) );
	}

	@Override
	public List<ICFBamClearTopDepObj> readAllClearTopDep( boolean forceRead ) {
		final String S_ProcName = "readAllClearTopDep";
		if( ( allClearTopDep == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> map = new HashMap<CFLibDbKeyHash256,ICFBamClearTopDepObj>();
			allClearTopDep = map;
			ICFBamClearTopDep[] recList = schema.getCFBamBackingStore().getTableClearTopDep().readAllDerived( null );
			ICFBamClearTopDep rec;
			ICFBamClearTopDepObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearTopDepObj realised = (ICFBamClearTopDepObj)obj.realise();
			}
		}
		int len = allClearTopDep.size();
		ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
		Iterator<ICFBamClearTopDepObj> valIter = allClearTopDep.values().iterator();
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
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
		List<ICFBamClearTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearTopDepObj> readCachedAllClearTopDep() {
		final String S_ProcName = "readCachedAllClearTopDep";
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>();
		if( allClearTopDep != null ) {
			int len = allClearTopDep.size();
			ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
			Iterator<ICFBamClearTopDepObj> valIter = allClearTopDep.values().iterator();
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
		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
	public ICFBamClearTopDepObj readClearTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readClearTopDepByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamClearTopDepObj readClearTopDepByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamClearTopDepObj obj = readClearTopDep( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readClearTopDepByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearTopDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearTopDepObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearTopDepObj realised = (ICFBamClearTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
		Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
		List<ICFBamClearTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readClearTopDepByClearDepIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByClearDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearTopDepByClearDepIdx";
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
		if( indexByClearDepIdx == null ) {
			indexByClearDepIdx = new HashMap< ICFBamClearDepByClearDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByClearDepIdx.containsKey( key ) ) {
			dict = indexByClearDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearTopDepObj>();
			ICFBamClearDepObj obj;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readDerivedByClearDepIdx( null,
				RelationId );
			ICFBamClearDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearTopDepObj realised = (ICFBamClearTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClearDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
		Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
		List<ICFBamClearTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readClearTopDepByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearTopDepByDefSchemaIdx";
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamClearDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearTopDepObj>();
			ICFBamClearDepObj obj;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamClearDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearTopDepObj realised = (ICFBamClearTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
		Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
		List<ICFBamClearTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByClrTopDepTblIdx( CFLibDbKeyHash256 TableId )
	{
		return( readClearTopDepByClrTopDepTblIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByClrTopDepTblIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearTopDepByClrTopDepTblIdx";
		ICFBamClearTopDepByClrTopDepTblIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByClrTopDepTblIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
		if( indexByClrTopDepTblIdx == null ) {
			indexByClrTopDepTblIdx = new HashMap< ICFBamClearTopDepByClrTopDepTblIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByClrTopDepTblIdx.containsKey( key ) ) {
			dict = indexByClrTopDepTblIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearTopDepObj>();
			ICFBamClearTopDepObj obj;
			ICFBamClearTopDep[] recList = schema.getCFBamBackingStore().getTableClearTopDep().readDerivedByClrTopDepTblIdx( null,
				TableId );
			ICFBamClearTopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearTopDepObj realised = (ICFBamClearTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClrTopDepTblIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
		Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
		List<ICFBamClearTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamClearTopDepObj readClearTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		return( readClearTopDepByUNameIdx( TableId,
			Name,
			false ) );
	}

	@Override
	public ICFBamClearTopDepObj readClearTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamClearTopDepByUNameIdxKey,
				ICFBamClearTopDepObj >();
		}
		ICFBamClearTopDepByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamClearTopDepObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamClearTopDep rec = schema.getCFBamBackingStore().getTableClearTopDep().readDerivedByUNameIdx( null,
				TableId,
				Name );
			if( rec != null ) {
				obj = (ICFBamClearTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamClearTopDepObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		return( readClearTopDepByPrevIdx( PrevId,
			false ) );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByPrevIdx( CFLibDbKeyHash256 PrevId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearTopDepByPrevIdx";
		ICFBamClearTopDepByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamClearTopDepByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByPrevIdx.containsKey( key ) ) {
			dict = indexByPrevIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearTopDepObj>();
			ICFBamClearTopDepObj obj;
			ICFBamClearTopDep[] recList = schema.getCFBamBackingStore().getTableClearTopDep().readDerivedByPrevIdx( null,
				PrevId );
			ICFBamClearTopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearTopDepObj realised = (ICFBamClearTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByPrevIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
		Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
		List<ICFBamClearTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByNextIdx( CFLibDbKeyHash256 NextId )
	{
		return( readClearTopDepByNextIdx( NextId,
			false ) );
	}

	@Override
	public List<ICFBamClearTopDepObj> readClearTopDepByNextIdx( CFLibDbKeyHash256 NextId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearTopDepByNextIdx";
		ICFBamClearTopDepByNextIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamClearTopDepByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( ( ! forceRead ) && indexByNextIdx.containsKey( key ) ) {
			dict = indexByNextIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearTopDepObj>();
			ICFBamClearTopDepObj obj;
			ICFBamClearTopDep[] recList = schema.getCFBamBackingStore().getTableClearTopDep().readDerivedByNextIdx( null,
				NextId );
			ICFBamClearTopDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearTopDepObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearTopDepObj realised = (ICFBamClearTopDepObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNextIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
		Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
		List<ICFBamClearTopDepObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamClearTopDepObj readCachedClearTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearTopDepObj obj = null;
		obj = readCachedClearTopDep( Id );
		return( obj );
	}

	@Override
	public List<ICFBamClearTopDepObj> readCachedClearTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedClearTopDepByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
				Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
			ICFBamClearTopDepObj obj;
			Iterator<ICFBamClearTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
	public List<ICFBamClearTopDepObj> readCachedClearTopDepByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedClearTopDepByClearDepIdx";
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>();
		if( indexByClearDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
			if( indexByClearDepIdx.containsKey( key ) ) {
				dict = indexByClearDepIdx.get( key );
				int len = dict.size();
				ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
				Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
			ICFBamClearTopDepObj obj;
			Iterator<ICFBamClearTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
	public List<ICFBamClearTopDepObj> readCachedClearTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedClearTopDepByDefSchemaIdx";
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
				Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
			ICFBamClearTopDepObj obj;
			Iterator<ICFBamClearTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
	public List<ICFBamClearTopDepObj> readCachedClearTopDepByClrTopDepTblIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedClearTopDepByClrTopDepTblIdx";
		ICFBamClearTopDepByClrTopDepTblIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByClrTopDepTblIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>();
		if( indexByClrTopDepTblIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
			if( indexByClrTopDepTblIdx.containsKey( key ) ) {
				dict = indexByClrTopDepTblIdx.get( key );
				int len = dict.size();
				ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
				Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
			ICFBamClearTopDepObj obj;
			Iterator<ICFBamClearTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
	public ICFBamClearTopDepObj readCachedClearTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamClearTopDepObj obj = null;
		ICFBamClearTopDepByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamClearTopDepObj> valIter = members.values().iterator();
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
			Iterator<ICFBamClearTopDepObj> valIter = members.values().iterator();
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
	public List<ICFBamClearTopDepObj> readCachedClearTopDepByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "readCachedClearTopDepByPrevIdx";
		ICFBamClearTopDepByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>();
		if( indexByPrevIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
			if( indexByPrevIdx.containsKey( key ) ) {
				dict = indexByPrevIdx.get( key );
				int len = dict.size();
				ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
				Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
			ICFBamClearTopDepObj obj;
			Iterator<ICFBamClearTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
	public List<ICFBamClearTopDepObj> readCachedClearTopDepByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "readCachedClearTopDepByNextIdx";
		ICFBamClearTopDepByNextIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		ArrayList<ICFBamClearTopDepObj> arrayList = new ArrayList<ICFBamClearTopDepObj>();
		if( indexByNextIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict;
			if( indexByNextIdx.containsKey( key ) ) {
				dict = indexByNextIdx.get( key );
				int len = dict.size();
				ICFBamClearTopDepObj arr[] = new ICFBamClearTopDepObj[len];
				Iterator<ICFBamClearTopDepObj> valIter = dict.values().iterator();
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
			ICFBamClearTopDepObj obj;
			Iterator<ICFBamClearTopDepObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearTopDepObj> cmp = new Comparator<ICFBamClearTopDepObj>() {
			@Override
			public int compare( ICFBamClearTopDepObj lhs, ICFBamClearTopDepObj rhs ) {
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
	public void deepDisposeClearTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearTopDepObj obj = readCachedClearTopDepByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeClearTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeClearTopDepByTenantIdx";
		ICFBamClearTopDepObj obj;
		List<ICFBamClearTopDepObj> arrayList = readCachedClearTopDepByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamClearTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearTopDepByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposeClearTopDepByClearDepIdx";
		ICFBamClearTopDepObj obj;
		List<ICFBamClearTopDepObj> arrayList = readCachedClearTopDepByClearDepIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamClearTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeClearTopDepByDefSchemaIdx";
		ICFBamClearTopDepObj obj;
		List<ICFBamClearTopDepObj> arrayList = readCachedClearTopDepByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamClearTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearTopDepByClrTopDepTblIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeClearTopDepByClrTopDepTblIdx";
		ICFBamClearTopDepObj obj;
		List<ICFBamClearTopDepObj> arrayList = readCachedClearTopDepByClrTopDepTblIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamClearTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamClearTopDepObj obj = readCachedClearTopDepByUNameIdx( TableId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeClearTopDepByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		final String S_ProcName = "deepDisposeClearTopDepByPrevIdx";
		ICFBamClearTopDepObj obj;
		List<ICFBamClearTopDepObj> arrayList = readCachedClearTopDepByPrevIdx( PrevId );
		if( arrayList != null )  {
			Iterator<ICFBamClearTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearTopDepByNextIdx( CFLibDbKeyHash256 NextId )
	{
		final String S_ProcName = "deepDisposeClearTopDepByNextIdx";
		ICFBamClearTopDepObj obj;
		List<ICFBamClearTopDepObj> arrayList = readCachedClearTopDepByNextIdx( NextId );
		if( arrayList != null )  {
			Iterator<ICFBamClearTopDepObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamClearTopDepObj updateClearTopDep( ICFBamClearTopDepObj Obj ) {
		ICFBamClearTopDepObj obj = Obj;
		schema.getCFBamBackingStore().getTableClearTopDep().updateClearTopDep( null,
			Obj.getClearTopDepRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getClearTopDepTableObj().getClassCode() ) {
			obj = (ICFBamClearTopDepObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteClearTopDep( ICFBamClearTopDepObj Obj ) {
		ICFBamClearTopDepObj obj = Obj;
		ICFBamClearTopDepObj prev = obj.getOptionalLookupPrev();
		ICFBamClearTopDepObj next = obj.getOptionalLookupNext();
		schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDep( null,
			obj.getClearTopDepRec() );
		Obj.forget();
		if( prev != null ) {
			prev.read( true );
		}
		if( next != null ) {
			next.read( true );
		}
	}

	@Override
	public void deleteClearTopDepByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearTopDepObj obj = readClearTopDep(Id);
		if( obj != null ) {
			ICFBamClearTopDepEditObj editObj = (ICFBamClearTopDepEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamClearTopDepEditObj)obj.beginEdit();
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
		deepDisposeClearTopDepByIdIdx( Id );
	}

	@Override
	public void deleteClearTopDepByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByTenantIdx( null,
				TenantId );
			Iterator<ICFBamClearTopDepObj> iter = dict.values().iterator();
			ICFBamClearTopDepObj obj;
			List<ICFBamClearTopDepObj> toForget = new LinkedList<ICFBamClearTopDepObj>();
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
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByTenantIdx( null,
				TenantId );
		}
		deepDisposeClearTopDepByTenantIdx( TenantId );
	}

	@Override
	public void deleteClearTopDepByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByClearDepIdx == null ) {
			indexByClearDepIdx = new HashMap< ICFBamClearDepByClearDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( indexByClearDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict = indexByClearDepIdx.get( key );
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByClearDepIdx( null,
				RelationId );
			Iterator<ICFBamClearTopDepObj> iter = dict.values().iterator();
			ICFBamClearTopDepObj obj;
			List<ICFBamClearTopDepObj> toForget = new LinkedList<ICFBamClearTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClearDepIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByClearDepIdx( null,
				RelationId );
		}
		deepDisposeClearTopDepByClearDepIdx( RelationId );
	}

	@Override
	public void deleteClearTopDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamClearDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamClearTopDepObj> iter = dict.values().iterator();
			ICFBamClearTopDepObj obj;
			List<ICFBamClearTopDepObj> toForget = new LinkedList<ICFBamClearTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeClearTopDepByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteClearTopDepByClrTopDepTblIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamClearTopDepByClrTopDepTblIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByClrTopDepTblIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByClrTopDepTblIdx == null ) {
			indexByClrTopDepTblIdx = new HashMap< ICFBamClearTopDepByClrTopDepTblIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( indexByClrTopDepTblIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict = indexByClrTopDepTblIdx.get( key );
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByClrTopDepTblIdx( null,
				TableId );
			Iterator<ICFBamClearTopDepObj> iter = dict.values().iterator();
			ICFBamClearTopDepObj obj;
			List<ICFBamClearTopDepObj> toForget = new LinkedList<ICFBamClearTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClrTopDepTblIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByClrTopDepTblIdx( null,
				TableId );
		}
		deepDisposeClearTopDepByClrTopDepTblIdx( TableId );
	}

	@Override
	public void deleteClearTopDepByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamClearTopDepByUNameIdxKey,
				ICFBamClearTopDepObj >();
		}
		ICFBamClearTopDepByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamClearTopDepObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByUNameIdx( null,
				TableId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByUNameIdx( null,
				TableId,
				Name );
		}
		deepDisposeClearTopDepByUNameIdx( TableId,
				Name );
	}

	@Override
	public void deleteClearTopDepByPrevIdx( CFLibDbKeyHash256 PrevId )
	{
		ICFBamClearTopDepByPrevIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByPrevIdxKey();
		key.setOptionalPrevId( PrevId );
		if( indexByPrevIdx == null ) {
			indexByPrevIdx = new HashMap< ICFBamClearTopDepByPrevIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( indexByPrevIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict = indexByPrevIdx.get( key );
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByPrevIdx( null,
				PrevId );
			Iterator<ICFBamClearTopDepObj> iter = dict.values().iterator();
			ICFBamClearTopDepObj obj;
			List<ICFBamClearTopDepObj> toForget = new LinkedList<ICFBamClearTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByPrevIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByPrevIdx( null,
				PrevId );
		}
		deepDisposeClearTopDepByPrevIdx( PrevId );
	}

	@Override
	public void deleteClearTopDepByNextIdx( CFLibDbKeyHash256 NextId )
	{
		ICFBamClearTopDepByNextIdxKey key = schema.getCFBamBackingStore().getFactoryClearTopDep().newByNextIdxKey();
		key.setOptionalNextId( NextId );
		if( indexByNextIdx == null ) {
			indexByNextIdx = new HashMap< ICFBamClearTopDepByNextIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearTopDepObj > >();
		}
		if( indexByNextIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearTopDepObj> dict = indexByNextIdx.get( key );
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByNextIdx( null,
				NextId );
			Iterator<ICFBamClearTopDepObj> iter = dict.values().iterator();
			ICFBamClearTopDepObj obj;
			List<ICFBamClearTopDepObj> toForget = new LinkedList<ICFBamClearTopDepObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNextIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearTopDep().deleteClearTopDepByNextIdx( null,
				NextId );
		}
		deepDisposeClearTopDepByNextIdx( NextId );
	}

	/**
	 *	Move the CFBamClearTopDepObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamClearTopDepObj refreshed cache instance.
	 */
	@Override
	public ICFBamClearTopDepObj moveUpClearTopDep( ICFBamClearTopDepObj Obj ) {
		ICFBamClearTopDepObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUpClearTopDep" );
		}
		ICFBamClearTopDep rec = schema.getCFBamBackingStore().getTableClearTopDep().moveRecUp( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getClearTopDepTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamClearTopDepObj)obj.realise();
			ICFBamClearTopDepObj prev = obj.getOptionalLookupPrev( true );
			ICFBamClearTopDepObj next = obj.getOptionalLookupNext( true );
			if( next != null ) {
				ICFBamClearTopDepObj gnext = next.getOptionalLookupNext( true );
			}
		}
		return( obj );
	}

	/**
	 *	Move the CFBamClearTopDepObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamClearTopDepObj refreshed cache instance.
	 */
	@Override
	public ICFBamClearTopDepObj moveDownClearTopDep( ICFBamClearTopDepObj Obj ) {
		ICFBamClearTopDepObj obj = null;
		if( null != Obj.getEdit() ) {
			throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDownClearTopDep" );
		}
		ICFBamClearTopDep rec = schema.getCFBamBackingStore().getTableClearTopDep().moveRecDown( null,
			Obj.getRequiredId(),
			Obj.getRec().getRequiredRevision() );
		if( rec != null ) {
			obj = schema.getClearTopDepTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			obj = (ICFBamClearTopDepObj)obj.realise();
			ICFBamClearTopDepObj prev = obj.getOptionalLookupPrev( true );
			if( prev != null ) {
				ICFBamClearTopDepObj gprev = prev.getOptionalLookupPrev( true );
			}
			ICFBamClearTopDepObj next = obj.getOptionalLookupNext( true );
		}
		return( obj );
	}
}