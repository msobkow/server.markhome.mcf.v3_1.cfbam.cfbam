// Description: Java 25 Table Object implementation for ClearSubDep3.

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

public class CFBamClearSubDep3TableObj
	implements ICFBamClearSubDep3TableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamClearSubDep3.CLASS_CODE;
	protected static final int backingClassCode = ICFBamClearSubDep3.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> members;
	private Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> allClearSubDep3;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > > indexByTenantIdx;
	private Map< ICFBamClearDepByClearDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > > indexByClearDepIdx;
	private Map< ICFBamClearDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > > indexByDefSchemaIdx;
	private Map< ICFBamClearSubDep3ByClearSubDep2IdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > > indexByClearSubDep2Idx;
	private Map< ICFBamClearSubDep3ByUNameIdxKey,
		ICFBamClearSubDep3Obj > indexByUNameIdx;
	public static String TABLE_NAME = "ClearSubDep3";
	public static String TABLE_DBNAME = "clrsubdep3";

	public CFBamClearSubDep3TableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep3Obj>();
		allClearSubDep3 = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClearSubDep2Idx = null;
		indexByUNameIdx = null;
	}

	public CFBamClearSubDep3TableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep3Obj>();
		allClearSubDep3 = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClearSubDep2Idx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamClearSubDep3TableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamClearSubDep3TableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allClearSubDep3 = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClearSubDep2Idx = null;
		indexByUNameIdx = null;
		List<ICFBamClearSubDep3Obj> toForget = new LinkedList<ICFBamClearSubDep3Obj>();
		ICFBamClearSubDep3Obj cur = null;
		Iterator<ICFBamClearSubDep3Obj> iter = members.values().iterator();
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
	 *	CFBamClearSubDep3Obj.
	 */
	@Override
	public ICFBamClearSubDep3Obj newInstance() {
		ICFBamClearSubDep3Obj inst = new CFBamClearSubDep3Obj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamClearSubDep3Obj.
	 */
	@Override
	public ICFBamClearSubDep3EditObj newEditInstance( ICFBamClearSubDep3Obj orig ) {
		ICFBamClearSubDep3EditObj edit = new CFBamClearSubDep3EditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamClearSubDep3Obj realiseClearSubDep3( ICFBamClearSubDep3Obj Obj ) {
		ICFBamClearSubDep3Obj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamClearSubDep3Obj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamClearSubDep3Obj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					indexByClearDepIdx.remove( keyClearDepIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByClearSubDep2Idx != null ) {
				ICFBamClearSubDep3ByClearSubDep2IdxKey keyClearSubDep2Idx =
					schema.getCFBamBackingStore().getFactoryClearSubDep3().newByClearSubDep2IdxKey();
				keyClearSubDep2Idx.setRequiredClearSubDep2Id( keepObj.getRequiredClearSubDep2Id() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapClearSubDep2Idx = indexByClearSubDep2Idx.get( keyClearSubDep2Idx );
				if( mapClearSubDep2Idx != null ) {
					mapClearSubDep2Idx.remove( keepObj.getPKey() );
					if( mapClearSubDep2Idx.size() <= 0 ) {
						indexByClearSubDep2Idx.remove( keyClearSubDep2Idx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearSubDep3ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep3().newByUNameIdxKey();
				keyUNameIdx.setRequiredClearSubDep2Id( keepObj.getRequiredClearSubDep2Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamClearSubDep3Obj)schema.getClearDepTableObj().realiseClearDep( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearSubDep2Idx != null ) {
				ICFBamClearSubDep3ByClearSubDep2IdxKey keyClearSubDep2Idx =
					schema.getCFBamBackingStore().getFactoryClearSubDep3().newByClearSubDep2IdxKey();
				keyClearSubDep2Idx.setRequiredClearSubDep2Id( keepObj.getRequiredClearSubDep2Id() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapClearSubDep2Idx = indexByClearSubDep2Idx.get( keyClearSubDep2Idx );
				if( mapClearSubDep2Idx != null ) {
					mapClearSubDep2Idx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearSubDep3ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep3().newByUNameIdxKey();
				keyUNameIdx.setRequiredClearSubDep2Id( keepObj.getRequiredClearSubDep2Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allClearSubDep3 != null ) {
				allClearSubDep3.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamClearSubDep3Obj)schema.getClearDepTableObj().realiseClearDep( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allClearSubDep3 != null ) {
				allClearSubDep3.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearSubDep2Idx != null ) {
				ICFBamClearSubDep3ByClearSubDep2IdxKey keyClearSubDep2Idx =
					schema.getCFBamBackingStore().getFactoryClearSubDep3().newByClearSubDep2IdxKey();
				keyClearSubDep2Idx.setRequiredClearSubDep2Id( keepObj.getRequiredClearSubDep2Id() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj > mapClearSubDep2Idx = indexByClearSubDep2Idx.get( keyClearSubDep2Idx );
				if( mapClearSubDep2Idx != null ) {
					mapClearSubDep2Idx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearSubDep3ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep3().newByUNameIdxKey();
				keyUNameIdx.setRequiredClearSubDep2Id( keepObj.getRequiredClearSubDep2Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamClearSubDep3Obj createClearSubDep3( ICFBamClearSubDep3Obj Obj ) {
		ICFBamClearSubDep3Obj obj = Obj;
		ICFBamClearSubDep3 rec = obj.getClearSubDep3Rec();
		schema.getCFBamBackingStore().getTableClearSubDep3().createClearSubDep3(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamClearSubDep3Obj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamClearSubDep3Obj readClearSubDep3( CFLibDbKeyHash256 pkey ) {
		return( readClearSubDep3( pkey, false ) );
	}

	@Override
	public ICFBamClearSubDep3Obj readClearSubDep3( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamClearSubDep3Obj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamClearSubDep3 readRec = schema.getCFBamBackingStore().getTableClearSubDep3().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamClearSubDep3Obj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamClearSubDep3Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamClearSubDep3Obj readCachedClearSubDep3( CFLibDbKeyHash256 pkey ) {
		ICFBamClearSubDep3Obj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeClearSubDep3( ICFBamClearSubDep3Obj obj )
	{
		final String S_ProcName = "CFBamClearSubDep3TableObj.reallyDeepDisposeClearSubDep3() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamClearSubDep3Obj existing = readCachedClearSubDep3( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamClearSubDep3ByClearSubDep2IdxKey keyClearSubDep2Idx = schema.getCFBamBackingStore().getFactoryClearSubDep3().newByClearSubDep2IdxKey();
		keyClearSubDep2Idx.setRequiredClearSubDep2Id( existing.getRequiredClearSubDep2Id() );

		ICFBamClearSubDep3ByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryClearSubDep3().newByUNameIdxKey();
		keyUNameIdx.setRequiredClearSubDep2Id( existing.getRequiredClearSubDep2Id() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );



		if( indexByClearSubDep2Idx != null ) {
			if( indexByClearSubDep2Idx.containsKey( keyClearSubDep2Idx ) ) {
				indexByClearSubDep2Idx.get( keyClearSubDep2Idx ).remove( pkey );
				if( indexByClearSubDep2Idx.get( keyClearSubDep2Idx ).size() <= 0 ) {
					indexByClearSubDep2Idx.remove( keyClearSubDep2Idx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


		schema.getClearDepTableObj().reallyDeepDisposeClearDep( obj );
	}
	@Override
	public void deepDisposeClearSubDep3( CFLibDbKeyHash256 pkey ) {
		ICFBamClearSubDep3Obj obj = readCachedClearSubDep3( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamClearSubDep3Obj lockClearSubDep3( CFLibDbKeyHash256 pkey ) {
		ICFBamClearSubDep3Obj locked = null;
		ICFBamClearSubDep3 lockRec = schema.getCFBamBackingStore().getTableClearSubDep3().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamClearSubDep3Obj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamClearSubDep3Obj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockClearSubDep3", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readAllClearSubDep3() {
		return( readAllClearSubDep3( false ) );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readAllClearSubDep3( boolean forceRead ) {
		final String S_ProcName = "readAllClearSubDep3";
		if( ( allClearSubDep3 == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> map = new HashMap<CFLibDbKeyHash256,ICFBamClearSubDep3Obj>();
			allClearSubDep3 = map;
			ICFBamClearSubDep3[] recList = schema.getCFBamBackingStore().getTableClearSubDep3().readAllDerived( null );
			ICFBamClearSubDep3 rec;
			ICFBamClearSubDep3Obj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep3Obj realised = (ICFBamClearSubDep3Obj)obj.realise();
			}
		}
		int len = allClearSubDep3.size();
		ICFBamClearSubDep3Obj arr[] = new ICFBamClearSubDep3Obj[len];
		Iterator<ICFBamClearSubDep3Obj> valIter = allClearSubDep3.values().iterator();
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
		ArrayList<ICFBamClearSubDep3Obj> arrayList = new ArrayList<ICFBamClearSubDep3Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep3Obj> cmp = new Comparator<ICFBamClearSubDep3Obj>() {
			@Override
			public int compare( ICFBamClearSubDep3Obj lhs, ICFBamClearSubDep3Obj rhs ) {
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
		List<ICFBamClearSubDep3Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readCachedAllClearSubDep3() {
		final String S_ProcName = "readCachedAllClearSubDep3";
		ArrayList<ICFBamClearSubDep3Obj> arrayList = new ArrayList<ICFBamClearSubDep3Obj>();
		if( allClearSubDep3 != null ) {
			int len = allClearSubDep3.size();
			ICFBamClearSubDep3Obj arr[] = new ICFBamClearSubDep3Obj[len];
			Iterator<ICFBamClearSubDep3Obj> valIter = allClearSubDep3.values().iterator();
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
		Comparator<ICFBamClearSubDep3Obj> cmp = new Comparator<ICFBamClearSubDep3Obj>() {
			public int compare( ICFBamClearSubDep3Obj lhs, ICFBamClearSubDep3Obj rhs ) {
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
	public ICFBamClearSubDep3Obj readClearSubDep3ByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readClearSubDep3ByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamClearSubDep3Obj readClearSubDep3ByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamClearSubDep3Obj obj = readClearSubDep3( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readClearSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readClearSubDep3ByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readClearSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep3ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep3Obj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep3Obj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep3Obj realised = (ICFBamClearSubDep3Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep3Obj arr[] = new ICFBamClearSubDep3Obj[len];
		Iterator<ICFBamClearSubDep3Obj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearSubDep3Obj> arrayList = new ArrayList<ICFBamClearSubDep3Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep3Obj> cmp = new Comparator<ICFBamClearSubDep3Obj>() {
			@Override
			public int compare( ICFBamClearSubDep3Obj lhs, ICFBamClearSubDep3Obj rhs ) {
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
		List<ICFBamClearSubDep3Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readClearSubDep3ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readClearSubDep3ByClearDepIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readClearSubDep3ByClearDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep3ByClearDepIdx";
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict;
		if( indexByClearDepIdx == null ) {
			indexByClearDepIdx = new HashMap< ICFBamClearDepByClearDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep3Obj > >();
		}
		if( ( ! forceRead ) && indexByClearDepIdx.containsKey( key ) ) {
			dict = indexByClearDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep3Obj>();
			ICFBamClearDepObj obj;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readDerivedByClearDepIdx( null,
				RelationId );
			ICFBamClearDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep3Obj realised = (ICFBamClearSubDep3Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClearDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep3Obj arr[] = new ICFBamClearSubDep3Obj[len];
		Iterator<ICFBamClearSubDep3Obj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearSubDep3Obj> arrayList = new ArrayList<ICFBamClearSubDep3Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep3Obj> cmp = new Comparator<ICFBamClearSubDep3Obj>() {
			@Override
			public int compare( ICFBamClearSubDep3Obj lhs, ICFBamClearSubDep3Obj rhs ) {
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
		List<ICFBamClearSubDep3Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readClearSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readClearSubDep3ByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readClearSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep3ByDefSchemaIdx";
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamClearDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep3Obj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep3Obj>();
			ICFBamClearDepObj obj;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamClearDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep3Obj realised = (ICFBamClearSubDep3Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep3Obj arr[] = new ICFBamClearSubDep3Obj[len];
		Iterator<ICFBamClearSubDep3Obj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearSubDep3Obj> arrayList = new ArrayList<ICFBamClearSubDep3Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep3Obj> cmp = new Comparator<ICFBamClearSubDep3Obj>() {
			@Override
			public int compare( ICFBamClearSubDep3Obj lhs, ICFBamClearSubDep3Obj rhs ) {
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
		List<ICFBamClearSubDep3Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readClearSubDep3ByClearSubDep2Idx( CFLibDbKeyHash256 ClearSubDep2Id )
	{
		return( readClearSubDep3ByClearSubDep2Idx( ClearSubDep2Id,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readClearSubDep3ByClearSubDep2Idx( CFLibDbKeyHash256 ClearSubDep2Id,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep3ByClearSubDep2Idx";
		ICFBamClearSubDep3ByClearSubDep2IdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep3().newByClearSubDep2IdxKey();
		key.setRequiredClearSubDep2Id( ClearSubDep2Id );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict;
		if( indexByClearSubDep2Idx == null ) {
			indexByClearSubDep2Idx = new HashMap< ICFBamClearSubDep3ByClearSubDep2IdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep3Obj > >();
		}
		if( ( ! forceRead ) && indexByClearSubDep2Idx.containsKey( key ) ) {
			dict = indexByClearSubDep2Idx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep3Obj>();
			ICFBamClearSubDep3Obj obj;
			ICFBamClearSubDep3[] recList = schema.getCFBamBackingStore().getTableClearSubDep3().readDerivedByClearSubDep2Idx( null,
				ClearSubDep2Id );
			ICFBamClearSubDep3 rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep3Obj realised = (ICFBamClearSubDep3Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClearSubDep2Idx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep3Obj arr[] = new ICFBamClearSubDep3Obj[len];
		Iterator<ICFBamClearSubDep3Obj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearSubDep3Obj> arrayList = new ArrayList<ICFBamClearSubDep3Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep3Obj> cmp = new Comparator<ICFBamClearSubDep3Obj>() {
			@Override
			public int compare( ICFBamClearSubDep3Obj lhs, ICFBamClearSubDep3Obj rhs ) {
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
		List<ICFBamClearSubDep3Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamClearSubDep3Obj readClearSubDep3ByUNameIdx( CFLibDbKeyHash256 ClearSubDep2Id,
		String Name )
	{
		return( readClearSubDep3ByUNameIdx( ClearSubDep2Id,
			Name,
			false ) );
	}

	@Override
	public ICFBamClearSubDep3Obj readClearSubDep3ByUNameIdx( CFLibDbKeyHash256 ClearSubDep2Id,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamClearSubDep3ByUNameIdxKey,
				ICFBamClearSubDep3Obj >();
		}
		ICFBamClearSubDep3ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep3().newByUNameIdxKey();
		key.setRequiredClearSubDep2Id( ClearSubDep2Id );
		key.setRequiredName( Name );
		ICFBamClearSubDep3Obj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamClearSubDep3 rec = schema.getCFBamBackingStore().getTableClearSubDep3().readDerivedByUNameIdx( null,
				ClearSubDep2Id,
				Name );
			if( rec != null ) {
				obj = (ICFBamClearSubDep3Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamClearSubDep3Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamClearSubDep3Obj readCachedClearSubDep3ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearSubDep3Obj obj = null;
		obj = readCachedClearSubDep3( Id );
		return( obj );
	}

	@Override
	public List<ICFBamClearSubDep3Obj> readCachedClearSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedClearSubDep3ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamClearSubDep3Obj> arrayList = new ArrayList<ICFBamClearSubDep3Obj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamClearSubDep3Obj arr[] = new ICFBamClearSubDep3Obj[len];
				Iterator<ICFBamClearSubDep3Obj> valIter = dict.values().iterator();
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
			ICFBamClearSubDep3Obj obj;
			Iterator<ICFBamClearSubDep3Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep3Obj> cmp = new Comparator<ICFBamClearSubDep3Obj>() {
			@Override
			public int compare( ICFBamClearSubDep3Obj lhs, ICFBamClearSubDep3Obj rhs ) {
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
	public List<ICFBamClearSubDep3Obj> readCachedClearSubDep3ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedClearSubDep3ByClearDepIdx";
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamClearSubDep3Obj> arrayList = new ArrayList<ICFBamClearSubDep3Obj>();
		if( indexByClearDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict;
			if( indexByClearDepIdx.containsKey( key ) ) {
				dict = indexByClearDepIdx.get( key );
				int len = dict.size();
				ICFBamClearSubDep3Obj arr[] = new ICFBamClearSubDep3Obj[len];
				Iterator<ICFBamClearSubDep3Obj> valIter = dict.values().iterator();
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
			ICFBamClearSubDep3Obj obj;
			Iterator<ICFBamClearSubDep3Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep3Obj> cmp = new Comparator<ICFBamClearSubDep3Obj>() {
			@Override
			public int compare( ICFBamClearSubDep3Obj lhs, ICFBamClearSubDep3Obj rhs ) {
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
	public List<ICFBamClearSubDep3Obj> readCachedClearSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedClearSubDep3ByDefSchemaIdx";
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamClearSubDep3Obj> arrayList = new ArrayList<ICFBamClearSubDep3Obj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamClearSubDep3Obj arr[] = new ICFBamClearSubDep3Obj[len];
				Iterator<ICFBamClearSubDep3Obj> valIter = dict.values().iterator();
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
			ICFBamClearSubDep3Obj obj;
			Iterator<ICFBamClearSubDep3Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep3Obj> cmp = new Comparator<ICFBamClearSubDep3Obj>() {
			@Override
			public int compare( ICFBamClearSubDep3Obj lhs, ICFBamClearSubDep3Obj rhs ) {
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
	public List<ICFBamClearSubDep3Obj> readCachedClearSubDep3ByClearSubDep2Idx( CFLibDbKeyHash256 ClearSubDep2Id )
	{
		final String S_ProcName = "readCachedClearSubDep3ByClearSubDep2Idx";
		ICFBamClearSubDep3ByClearSubDep2IdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep3().newByClearSubDep2IdxKey();
		key.setRequiredClearSubDep2Id( ClearSubDep2Id );
		ArrayList<ICFBamClearSubDep3Obj> arrayList = new ArrayList<ICFBamClearSubDep3Obj>();
		if( indexByClearSubDep2Idx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict;
			if( indexByClearSubDep2Idx.containsKey( key ) ) {
				dict = indexByClearSubDep2Idx.get( key );
				int len = dict.size();
				ICFBamClearSubDep3Obj arr[] = new ICFBamClearSubDep3Obj[len];
				Iterator<ICFBamClearSubDep3Obj> valIter = dict.values().iterator();
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
			ICFBamClearSubDep3Obj obj;
			Iterator<ICFBamClearSubDep3Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep3Obj> cmp = new Comparator<ICFBamClearSubDep3Obj>() {
			@Override
			public int compare( ICFBamClearSubDep3Obj lhs, ICFBamClearSubDep3Obj rhs ) {
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
	public ICFBamClearSubDep3Obj readCachedClearSubDep3ByUNameIdx( CFLibDbKeyHash256 ClearSubDep2Id,
		String Name )
	{
		ICFBamClearSubDep3Obj obj = null;
		ICFBamClearSubDep3ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep3().newByUNameIdxKey();
		key.setRequiredClearSubDep2Id( ClearSubDep2Id );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamClearSubDep3Obj> valIter = members.values().iterator();
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
			Iterator<ICFBamClearSubDep3Obj> valIter = members.values().iterator();
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
	public void deepDisposeClearSubDep3ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearSubDep3Obj obj = readCachedClearSubDep3ByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeClearSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeClearSubDep3ByTenantIdx";
		ICFBamClearSubDep3Obj obj;
		List<ICFBamClearSubDep3Obj> arrayList = readCachedClearSubDep3ByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep3Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep3ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposeClearSubDep3ByClearDepIdx";
		ICFBamClearSubDep3Obj obj;
		List<ICFBamClearSubDep3Obj> arrayList = readCachedClearSubDep3ByClearDepIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep3Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeClearSubDep3ByDefSchemaIdx";
		ICFBamClearSubDep3Obj obj;
		List<ICFBamClearSubDep3Obj> arrayList = readCachedClearSubDep3ByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep3Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep3ByClearSubDep2Idx( CFLibDbKeyHash256 ClearSubDep2Id )
	{
		final String S_ProcName = "deepDisposeClearSubDep3ByClearSubDep2Idx";
		ICFBamClearSubDep3Obj obj;
		List<ICFBamClearSubDep3Obj> arrayList = readCachedClearSubDep3ByClearSubDep2Idx( ClearSubDep2Id );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep3Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep3ByUNameIdx( CFLibDbKeyHash256 ClearSubDep2Id,
		String Name )
	{
		ICFBamClearSubDep3Obj obj = readCachedClearSubDep3ByUNameIdx( ClearSubDep2Id,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamClearSubDep3Obj updateClearSubDep3( ICFBamClearSubDep3Obj Obj ) {
		ICFBamClearSubDep3Obj obj = Obj;
		schema.getCFBamBackingStore().getTableClearSubDep3().updateClearSubDep3( null,
			Obj.getClearSubDep3Rec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getClearSubDep3TableObj().getClassCode() ) {
			obj = (ICFBamClearSubDep3Obj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteClearSubDep3( ICFBamClearSubDep3Obj Obj ) {
		ICFBamClearSubDep3Obj obj = Obj;
		schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3( null,
			obj.getClearSubDep3Rec() );
		Obj.forget();
	}

	@Override
	public void deleteClearSubDep3ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearSubDep3Obj obj = readClearSubDep3(Id);
		if( obj != null ) {
			ICFBamClearSubDep3EditObj editObj = (ICFBamClearSubDep3EditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamClearSubDep3EditObj)obj.beginEdit();
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
		deepDisposeClearSubDep3ByIdIdx( Id );
	}

	@Override
	public void deleteClearSubDep3ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep3Obj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3ByTenantIdx( null,
				TenantId );
			Iterator<ICFBamClearSubDep3Obj> iter = dict.values().iterator();
			ICFBamClearSubDep3Obj obj;
			List<ICFBamClearSubDep3Obj> toForget = new LinkedList<ICFBamClearSubDep3Obj>();
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
			schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3ByTenantIdx( null,
				TenantId );
		}
		deepDisposeClearSubDep3ByTenantIdx( TenantId );
	}

	@Override
	public void deleteClearSubDep3ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByClearDepIdx == null ) {
			indexByClearDepIdx = new HashMap< ICFBamClearDepByClearDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep3Obj > >();
		}
		if( indexByClearDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict = indexByClearDepIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3ByClearDepIdx( null,
				RelationId );
			Iterator<ICFBamClearSubDep3Obj> iter = dict.values().iterator();
			ICFBamClearSubDep3Obj obj;
			List<ICFBamClearSubDep3Obj> toForget = new LinkedList<ICFBamClearSubDep3Obj>();
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
			schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3ByClearDepIdx( null,
				RelationId );
		}
		deepDisposeClearSubDep3ByClearDepIdx( RelationId );
	}

	@Override
	public void deleteClearSubDep3ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamClearDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep3Obj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3ByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamClearSubDep3Obj> iter = dict.values().iterator();
			ICFBamClearSubDep3Obj obj;
			List<ICFBamClearSubDep3Obj> toForget = new LinkedList<ICFBamClearSubDep3Obj>();
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
			schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3ByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeClearSubDep3ByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteClearSubDep3ByClearSubDep2Idx( CFLibDbKeyHash256 ClearSubDep2Id )
	{
		ICFBamClearSubDep3ByClearSubDep2IdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep3().newByClearSubDep2IdxKey();
		key.setRequiredClearSubDep2Id( ClearSubDep2Id );
		if( indexByClearSubDep2Idx == null ) {
			indexByClearSubDep2Idx = new HashMap< ICFBamClearSubDep3ByClearSubDep2IdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep3Obj > >();
		}
		if( indexByClearSubDep2Idx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep3Obj> dict = indexByClearSubDep2Idx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3ByClearSubDep2Idx( null,
				ClearSubDep2Id );
			Iterator<ICFBamClearSubDep3Obj> iter = dict.values().iterator();
			ICFBamClearSubDep3Obj obj;
			List<ICFBamClearSubDep3Obj> toForget = new LinkedList<ICFBamClearSubDep3Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClearSubDep2Idx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3ByClearSubDep2Idx( null,
				ClearSubDep2Id );
		}
		deepDisposeClearSubDep3ByClearSubDep2Idx( ClearSubDep2Id );
	}

	@Override
	public void deleteClearSubDep3ByUNameIdx( CFLibDbKeyHash256 ClearSubDep2Id,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamClearSubDep3ByUNameIdxKey,
				ICFBamClearSubDep3Obj >();
		}
		ICFBamClearSubDep3ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep3().newByUNameIdxKey();
		key.setRequiredClearSubDep2Id( ClearSubDep2Id );
		key.setRequiredName( Name );
		ICFBamClearSubDep3Obj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3ByUNameIdx( null,
				ClearSubDep2Id,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableClearSubDep3().deleteClearSubDep3ByUNameIdx( null,
				ClearSubDep2Id,
				Name );
		}
		deepDisposeClearSubDep3ByUNameIdx( ClearSubDep2Id,
				Name );
	}
}